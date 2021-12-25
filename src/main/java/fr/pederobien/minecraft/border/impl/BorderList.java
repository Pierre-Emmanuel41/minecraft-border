package fr.pederobien.minecraft.border.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import org.bukkit.World;

import fr.pederobien.minecraft.border.interfaces.IBorder;
import fr.pederobien.minecraft.border.interfaces.IBorderList;

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
	public void add(IBorder configuration) {
		addBorder(configuration);
	}

	@Override
	public IBorder remove(String name) {
		return removeBorder(name);
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
			while (iterator.hasNext()) {
				Map.Entry<String, IBorder> entry = iterator.next();
				if (entry.getValue().getWorld().equals(world)) {
					iterator.remove();
					return entry.getValue();
				}
			}
		} finally {
			lock.unlock();
		}
		return null;
	}

	@Override
	public void clear() {
		borders.clear();
	}

	@Override
	public Optional<IBorder> getBorder(World world) {
		lock.lock();
		try {
			Iterator<Map.Entry<String, IBorder>> iterator = borders.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, IBorder> entry = iterator.next();
				if (entry.getValue().getWorld().equals(world))
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
