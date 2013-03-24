nikita
======

a java serialize/deserialize framework base on protocol buffer,fast and small

appetite
======

如果你还在为memcached居高不下的内存使用量而担忧，可以试下nikita，序列化同样一个大小的java对象，nikita可能比其他序列化方式省下超过50%的内存（比如说TestCase里面的json）

如果你还在为反序列化时巨大的性能开销而伤脑筋，不如换一种方式来思考：对象的结构本来就应该为序列化正反双方所共识，像一份协议一样，比如说，第二次反序列化同一类型的对象时，可以借用前一次的成果加速
