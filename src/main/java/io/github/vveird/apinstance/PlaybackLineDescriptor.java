package io.github.vveird.apinstance;

import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlaybackLineDescriptor {
	
	static AtomicLong counter = new AtomicLong(100);
	
	private long id;
	private String name;
	private String type;
	private boolean active=false;
	private boolean loop=false;
	private List<String> queue;
	
	public static AtomicLong getCounter() {
		return counter;
	}

	public static void setCounter(AtomicLong counter) {
		PlaybackLineDescriptor.counter = counter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public List<String> getQueue() {
		return queue;
	}

	public void setQueue(List<String> queue) {
		this.queue = queue;
	}

	public long getId() {
		return id;
	}

	private PlaybackLineDescriptor(PlaybackLineDescriptorBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.type = builder.type;
		this.active = builder.active;
		this.loop = builder.loop;
		this.queue = builder.queue;
	}
	
	public PlaybackLineDescriptor() {
		PlaybackLineDescriptor pld = new PlaybackLineDescriptorBuilder().id(-999).name("Does not exist").build();
		this.id = pld.id;
		this.name = pld.name;
	}
	
	public static class PlaybackLineDescriptorBuilder {
		private long id;
		private String name = "";
		private String type = "";
		private boolean active=false;
		private boolean loop=false;
		private List<String> queue = new LinkedList<String>();
		
		public PlaybackLineDescriptorBuilder id() {
			this.id = PlaybackLineDescriptor.counter.getAndIncrement();
			return this;
		}
		
		public PlaybackLineDescriptorBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public PlaybackLineDescriptorBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public PlaybackLineDescriptorBuilder type(String type) {
			this.type = type;
			return this;
		}
		
		public PlaybackLineDescriptorBuilder active(boolean active) {
			this.active = active;
			return this;
		}
		
		public PlaybackLineDescriptorBuilder loop(boolean loop) {
			this.loop = loop;
			return this;
		}
		
		public PlaybackLineDescriptorBuilder queue(List<String> queue) {
			this.queue = queue;
			return this;
		}
		
		public PlaybackLineDescriptor build() {
			return new PlaybackLineDescriptor(this);
		}
	}
}
