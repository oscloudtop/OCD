package cn.oddcloud.www.oddccloudtelevision.Aplayer.model;


import java.util.Comparator;

public class FileItemInfo {
	public String name;
	public String path;
	public boolean isDirectory;

	public static class SortComparator implements Comparator {
		@Override
		public int compare(Object o, Object t1) {
			FileItemInfo fileItemInfo0 = (FileItemInfo) o;
			FileItemInfo fileItemInfo1 = (FileItemInfo) t1;

			return fileItemInfo0.name.compareTo(fileItemInfo1.name);
		}
	}
}
