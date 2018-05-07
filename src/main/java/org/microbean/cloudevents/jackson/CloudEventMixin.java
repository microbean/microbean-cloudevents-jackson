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

import java.net.URI;

import java.time.Instant;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(content = JsonInclude.Include.NON_NULL, value = JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
  "cloudEventsVersion",
  "eventType",
  "eventTypeVersion",
  "source",
  "eventID",
  "eventTime",
  "extensions",
  "contentType",
  "schemaURL",
  "data"
})
class CloudEventMixin {

  @JsonCreator
  private CloudEventMixin(@JsonProperty("cloudEventsVersion") final String cloudEventsVersion,
                          @JsonProperty("eventType") final String eventType,
                          @JsonProperty("eventTypeVersion") final String eventTypeVersion,
                          @JsonProperty("source") final URI source,
                          @JsonProperty("eventID") final String eventID,
                          @JsonProperty("eventTime") final Instant eventTime,
                          @JsonProperty("extensions") final Map<? extends String, ?> extensions,
                          @JsonProperty("contentType") final String contentType,
                          @JsonProperty("schemaURL") final URI schemaURL,
                          @JsonProperty("data") final Object data) {
    super();
  }
  
}
