package org.wuzl.test.zkClient;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.Watcher.Event.KeeperState;

public class TestZkClient {
	private final ZkClient client;

	TestZkClient() {
		client = new ZkClient("localhost:2181");
	}

	/**
	 * 新建持久化的路径
	 * 
	 * @param path
	 */
	public void createPersistent(String path) {
		try {
			client.createPersistent(path, true);
		} catch (ZkNodeExistsException e) {
		}
	}

	/**
	 * 新建临时的路径 临时路径不能有子节点
	 * 
	 * @param path
	 */
	public void createEphemeral(String path) {
		try {
			client.createEphemeral(path);
		} catch (ZkNodeExistsException e) {
		}
	}

	public void delete(String path) {
		try {
			client.delete(path);
		} catch (ZkNoNodeException e) {
		}
	}

	public List<String> getChildren(String path) {
		try {
			return client.getChildren(path);
		} catch (ZkNoNodeException e) {
			return null;
		}
	}

	public void doClose() {
		client.close();
	}

	public static void main(String[] args) {
		TestZkClient test = new TestZkClient();
		test.createPersistent("/wuzl/1/1");
		System.out.println(test.getChildren("/"));
		test.createPersistent("/wuzl");
		System.out.println(test.getChildren("/"));
	}
}
