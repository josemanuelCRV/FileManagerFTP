package com.jmclabs.builder;

public class Resource {

	private final String server; // required
	private final String localpath; // required
	private final String remotepath; // required
	private final int age; // optional
	private final String phone; // optional
	private final String address; // optional

	private Resource(ResourceBuilder builder) {
		this.server = builder.server;
		this.localpath = builder.localpath;
		this.remotepath = builder.remotepath;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}

	public String getServer() {
		return server;
	}

	public String getLocalPath() {
		return localpath;
	}

	public String getRemotePath() {
		return remotepath;
	}

	public int getAge() {
		return age;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public static class ResourceBuilder {
		private String server;
		private String localpath;
		private String remotepath;
		private int age;
		private String phone;
		private String address;

		/**
		 * Con atributos finales obligamos al cliente a completar los parámetros
		 * en el constructor. Y quitaríamos los getters de estos atributos ya
		 * que son obtenidos por parámetro en el constructor. Ejemplo:
		 */
		// private final String server;
		// private final String localpath;
		// private final String remotepath;

		// public ResourceBuilder(String server, String localpath, String
		// remotepath) {
		// this.server = server;
		// this.localpath = localpath;
		// this.remotepath = remotepath;
		// }

		/**
		 * 
		 */
		public ResourceBuilder() {
			super();
		}

		public ResourceBuilder server(String servername) {
			this.server = servername;
			return this;
		}

		public ResourceBuilder localpath(String localpath) {
			this.localpath = localpath;
			return this;
		}

		public ResourceBuilder remotepath(String remotepath) {
			this.remotepath = remotepath;
			return this;
		}

		public ResourceBuilder age(int age) {
			this.age = age;
			return this;
		}

		public ResourceBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public ResourceBuilder address(String address) {
			this.address = address;
			return this;
		}

		public Resource build() {

			return new Resource(this);
		}

	}
}
