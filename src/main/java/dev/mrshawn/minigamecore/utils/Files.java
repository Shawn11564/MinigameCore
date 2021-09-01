package dev.mrshawn.minigamecore.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Files {

	public static void copy(String sourceDirectoryLocation, String destinationDirectoryLocation)
			throws IOException {
		java.nio.file.Files.walk(Paths.get(sourceDirectoryLocation))
				.forEach(source -> {
					Path destination = Paths.get(destinationDirectoryLocation, source.toString()
							.substring(sourceDirectoryLocation.length()));
					try {
						java.nio.file.Files.copy(source, destination);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	public static void delete(File file) {
		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles != null) {
				for (File childFile : childFiles) {
					delete(childFile);
				}
			}
		} else {
			file.delete();
		}
	}

}
