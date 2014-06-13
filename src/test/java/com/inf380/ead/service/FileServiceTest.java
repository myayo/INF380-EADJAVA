package com.inf380.ead.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileServiceTest {

	@Test
	public void testCreateDirectory() throws IOException{
		FileService fileService = new FileService();
		String pathname="src/test/resources/directoryTest";
		fileService.createOrUpdateFile(pathname, "directory", null);
		File file = new File(pathname);
		assertTrue(file.exists());
		assertTrue(file.isDirectory());
		fileService.deleteFile(pathname);
		assertFalse(file.exists());
	}
	
	@Test
	public void testCreateFile() throws IOException{
		FileService fileService = new FileService();
		String pathname="src/test/resources/Main.java";
		fileService.createOrUpdateFile(pathname, "file", "public void");
		File file = new File(pathname);
		assertTrue(file.exists());
		assertTrue(file.isFile());
		assertTrue(file.length()>0);
		fileService.deleteFile(pathname);
		assertFalse(file.exists());
	}
}
