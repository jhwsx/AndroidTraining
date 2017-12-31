package com.example.t4_test_apps;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertTrue;

public class JUnitRuleTester {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Rule
  public JUnitCustomRule myRule = new JUnitCustomRule();

  @Test
  public void testUsingTempFolder() throws IOException {
    File createdFolder = folder.newFolder("newfolder");
    File createdFile = folder.newFile("myfilefile.txt");
    assertTrue(createdFile.exists());
  }

}