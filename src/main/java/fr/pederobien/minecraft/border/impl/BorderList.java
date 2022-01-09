package fr.pederobien.minecraft.border.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import org.bukkit.World;

import fr.pederobien.minecraft.border.event.BorderListBorderAddPostEvent;
import fr.pederobien.minecraft.border.event.BorderListBorderRemovePostEvent;
import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;
import fr.pederobien.utils.event.EventManager;

public class BorderList implements IBorderList {
	private String name;
	private Map<String, IBorder> borders;
	private Lock lock;

	/**
	 * Creates a borders list.
	 * 
	 * @param name The name of this list.
	 */
	public BorderList(String name) {
		this.name = name;
		borders = new LinkedHashMap<String, IBorder>();
		lock = new ReentrantLock(true);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void add(IBorder border) {
		addBorder(border);
		EventManager.callEvent(new BorderListBorderAddPostEvent(this, border));
	}

	@Override
	public IBorder remove(String name) {
		IBorder border = removeBorder(name);
		if (border != null)
			EventManager.callEvent(new BorderListBorderRemovePostEvent(this, border));
		return border;
	}

	@Override
	public boolean remove(IBorder configuration) {
		return remove(configuration.getName()) != null;
	}

	@Override
	public IBorder remove(World world) {
		lock.lock();
		try {
			Iterator<Map.Entry<String, IBorder>> iterator = borders.entrySet().iterator();
			IBorder border = null;
			while (iterator.hasNext()) {
				Map.Entry<String, IBorder> entry = iterator.next();
				if (entry.getValue().getWorld().get().equals(world)) {
					border = entry.getValue();
				}
			}

			if (border != null)
				remove(border);
		} finally {
			lock.unlock();
		}
		return null;
	}

	@Override
	public void clear() {
		lock.lock();
		try {
			Set<String> names = new HashSet<String>(borders.keySet());
			for (String name : names) {
				IBorder border = borders.remove(name);
				EventManager.callEvent(new BorderListBorderRemovePostEvent(this, border));
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public Optional<IBorder> getBorder(World world) {
		lock.lock();
		try {
			Iterator<Map.Entry<String, IBorder>> iterator = borders.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, IBorder> entry = iterator.next();
				if (entry.getValue().getWorld().get().equals(world))
					return Optional.of(entry.getValue());
			}
		} finally {
			lock.unlock();
		}
		return Optional.empty();
	}

	@Override
	public Optional<IBorder> getBorder(String name) {
		lock.lock();
		try {
			Iterator<Map.Entry<String, IBorder>> iterator = borders.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, IBorder> entry = iterator.next();
				if (entry.getValue().getName().equals(name))
					return Optional.of(entry.getValue());
			}
		} finally {
			lock.unlock();
		}
		return Optional.empty();
	}

	@Override
	public Stream<IBorder> stream() {
		return borders.values().stream();
	}

	@Override
	public List<IBorder> toList() {
		return new ArrayList<IBorder>(borders.values());
	}

	/**
	 * Thread safe operation that adds a border to the borders list.
	 * 
	 * @param border The border to add.
	 * 
	 * @throws BorderAlreadyRegisteredException if a border is already registered for the border name.
	 */
	private void addBorder(IBorder border) {
		lock.lock();
		try {
			if (borders.get(border.getName()) != null)
				throw new BorderAlreadyRegisteredException(this, border);

			Optional<IBorder> optBorder = getBorder(border.getWorld().get());
			if (optBorder.isPresent())
				remove(optBorder.get());

			borders.put(border.getName(), border);
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Thread safe operation that removes a border from the borders list.
	 * 
	 * @param name The border name to remove.
	 * 
	 * @return The border associated to the given name if registered, null otherwise.
	 */
	private IBorder removeBorder(String name) {
		lock.lock();
		try {
			return borders.remove(name);
		} finally {
			lock.unlock();
		}
	}
}
