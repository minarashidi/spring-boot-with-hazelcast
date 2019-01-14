## Spring Boot with Hazelcast

Hazelcast is a powerful, leading, distributed and in-memory data grid for managing data and performing parallel execution 
to increase our application performance.

Good to know:

* It is written in Java.
* Unlike some other in-memory databases — Hazelcast is multiple-threaded, which means it can benefit from all available CPU cores.
* Unlike other in-memory data grids — it is designed to be used in distributed environment. It supports unlimited number of maps and caches per cluster.
* Based on the benchmarks Hazelcast is up to 56% faster than Redis in getting data, and up to 44% faster than Redis in setting data.


In hazelcast member, After adding the related dependencies we need to configure Hazelcast instance.
There are two ways to do that:

* Through Java configuration which I used this approach here.
* Through creating hazelcast.xml configuration file.

Since instance is configured — now we can access Hazelcast and manipulate with data.
One important thing — you can get data stored in this cache even from different application instance. 
To do this — I’ll change the app port to be 8081, and run another instance of the app, 
and then will try to fetch all the data from Hazelcast that we stored before.