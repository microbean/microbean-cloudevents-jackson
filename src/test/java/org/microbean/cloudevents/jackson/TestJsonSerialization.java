/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2018 microBean.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.microbean.cloudevents.jackson;

import java.io.IOException;

import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.Before;
import org.junit.Test;

import org.microbean.cloudevents.CloudEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestJsonSerialization {

  private final Path referenceFiles;

  private ObjectMapper objectMapper;
  
  public TestJsonSerialization() throws URISyntaxException {
    super();
    this.referenceFiles = Paths.get(Thread.currentThread().getContextClassLoader().getResource("TestJsonSerialization/").toURI());
    assert this.referenceFiles != null;
  }

  @Before
  public void createObjectMapper() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new CloudEventsModule());
    this.objectMapper.registerModule(new JavaTimeModule());
    this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
  }

  @Test
  public void testRead() throws IOException {
    final Path testReadJson = this.referenceFiles.resolve("testRead.json");
    assertNotNull(testReadJson);
    CloudEvent cloudEvent = null;
    try (final MappingIterator<CloudEvent> iterator = objectMapper.readerFor(CloudEvent.class).readValues(testReadJson.toUri().toURL())) {
      assertNotNull(iterator);
      assertTrue(iterator.hasNext());
      cloudEvent = iterator.next();
      assertFalse(iterator.hasNext());
    }
    assertNotNull(cloudEvent);
    assertEquals("0.1", cloudEvent.getCloudEventsVersion());
    assertEquals("com.example.someevent", cloudEvent.getEventType());
    assertEquals("1.0", cloudEvent.getEventTypeVersion());
    assertEquals("/mycontext", String.valueOf(cloudEvent.getSource()));
    assertEquals("A234-1234-1234", cloudEvent.getEventID());
    assertNotNull(cloudEvent.getEventTime()); // TODO: verify parsing
    final Map<? extends String, ?> extensions = cloudEvent.getExtensions();
    assertNotNull(extensions);
    assertFalse(extensions.isEmpty());
    assertEquals(1, extensions.size());
    assertEquals("value", extensions.get("comExampleExtension"));
    assertEquals("text/xml", cloudEvent.getContentType());
    assertEquals("<much wow=\"xml\"/>", cloudEvent.getData());
  }
  
}
