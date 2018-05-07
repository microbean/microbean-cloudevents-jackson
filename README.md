# microBean CloudEvents Jackson Integration

This project provides [Jackson mixin classes][mixins] and a
[module][jackson-module] [implementation][cloudevents-module] that
registers them that define how [microBean
CloudEvents][microbean-cloudevents] are serialized and deserialized to
and from [their canonical JSON format][cloudevents-json].

[mixins]: https://github.com/FasterXML/jackson-docs/wiki/JacksonMixInAnnotations
[jackson-module]: https://fasterxml.github.io/jackson-databind/javadoc/2.9/com/fasterxml/jackson/databind/Module.html
[cloudevents-module]: https://microbean.github.io/microbean-cloudevents-jackson/apidocs/org/microbean/cloudevents/jackson/CloudEventsModule.html
[microbean-cloudevents]: https://microbean.github.io/microbean-cloudevents/
[cloudevents-json]: https://github.com/cloudevents/spec/blob/master/json-format.md
