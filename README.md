# Data modeling for reusable and maintainable shiny application:

Suppose we want to build an application let's say an Early Warning System for diseases outbreak.
By the time, It becomes large and complex then difficult to maintain.
Modeling should come to rescue because It allows data scientists, engineers to visualize entire system,
to reason about the aspects of those systems and correct potential misconceptions.

Your data model (relationships between elements of the system) will probably encompass
the following components:

* Diseases you want to monitor

* Geographical sites from which you collect the data.

* Algorithms which are applied to diseases for producing analysis of interests

How you may model that in `java` so that It will make your shiny app more readable
and by the way reusable in a large scale shiny web development project?

## Solution:

### Let's create our java classes for our example:

* A disease has an unique identifier
* A disease can be loaded by its name, by site or all at once
* A disease has an evolution of cases number for a given geographical site.
* A disease has an historical alerts for all monitored sites
* A disease has an historical percentile rank value for all monitored sites
* A disease is monitored in one or several geographical sites.
* A site has an unique identifier
* A site can be loaded by its name, by disease or all at once
* A site is displayed into a map (and must be formatted for that).
* For a particular site, one or more diseases are monitored.
* A site may be in alert or not for a given epidemiological week
* A percentage of site in alert based on a given algorithm
  are displayed during period of interests

Based on the facts above , create 02 classes from within a package.
This package is itself contained in a java project (I use eclipse):

* Create a class `Diseases`

* Create a class `Sites`

We've just created 02 simple classes with different methods as you can see in `Diseases.java`,
`Sites.java`.
