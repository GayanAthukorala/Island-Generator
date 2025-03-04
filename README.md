# Island Generator
This is an Island Generator project created to practice Agile development and OOP best practices. The Island Generator takes in various parameters (or none!), and then creates a randomized and reproducable island. Cities can also be added to the island map generator, and Dijkstra's algorithm was used to connect these cities
together via the shortest possible path

![Island Gen](https://github.com/GayanAthukorala/Island-Generator/assets/73492549/d6a8e0a5-02ef-457b-a241-304e1f08b217)


### Installation instructions

This product is handled by Maven, as a multi-module project.

To install the different tooling on your computer, in the directory you cloned this project in simply run:

```
mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

### Generator
**NOTE THE GENERATOR WILL TAKE LONG AS IT IS RELAXING THE MESH MULTIPLE TIMES.**

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one single argument (so far), the name of the file where the generated mesh will be stored as binary.


```
cd generator 
java -jar generator.jar sample.mesh
ls -lh sample.mesh
```

### Island
To run the island, go to the `island` directory, and use `java -jar` to run the product. This product takes Mesh as an Input and Outputs a mesh to visualize. 
```
java -jar island.jar -I ../generator/sample.mesh -O island.mesh
```
```
Options:
------------------------------------
--help
For the Help Menu
------------------------------------
Shape "-shape xx"
- Options Here: 
Moon
Cross
Circle
Oval
Heart
------------------------------------
Altitude "-altitude xx"
- Options Here: 
Mountain
Hill
Flat
------------------------------------
Maximum # Lakes "-lakes xx"
Options: Integer Value
------------------------------------
# of Aquifers "-aquifers xx"
Options: Integer Value
------------------------------------
Type of Island Soil "-soil xx"
- Options Here: 
Wet
Dry
Normal
------------------------------------
Type of Biomes "-biomes xx"
- Options Here: 
Desert
Grassland
Deciduous
Taiga
Tundra
Forest
TemperateRain
Tropical
Savana
------------------------------------
Type of Heatmap "-heatmap xx"
- Options Here: 
Elevation
Mositure
------------------------------------
Number of Cities "-cities x"
This is the number of cities that will be generated on the island
```

### Visualizer

To visualize an existing mesh, go the the `visualizer` directory, and use `java -jar` to run the product. The product take two arguments (so far): the file containing the mesh, and the name of the file to store the visualization (as an SVG image).

```
cd visualizer 
java -jar visualizer.jar ../island/island.mesh sample.svg

... (lots of debug information printed to stdout) ...

ls -lh sample.svg
```
To viualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tool slike `rsvg-convert`

### Definition of Done

-- Feature Compiles without error and has meaningful contribution to MVP and does not interfere with the working status of previous features --

### Product Backlog

| Id  | Feature title                                                                                         | Who?                       | Start    | End      | Status |
|:---:|-------------------------------------------------------------------------------------------------------|----------------------------|----------|----------|--------|
| F0  | Segment Generated                                                                                     | Raymond Ma                 | Feb 5    | Feb 9    | Done   |
| F1  | Segment Colour Added                                                                                  | Gayan Athukorala           | Feb 9    | Feb 9    | Done   |
| F2  | Segment Visualizer                                                                                    | Rhea Gokhale               | Feb 9    | Feb 9    | Done   |
| F3  | Creating a mesh ADT                                                                                   | Raymond Ma                 | Feb 16   | Feb 16   | Done   |
| F4  | Producing full meshes                                                                                 | Gayan Athukorala           | Feb 17   | Feb 22   | Done   |
| F5  | Implementing Debug Mode                                                                               | Gayan + Rhea               | Feb 22   | Feb 24   | Done   |
| F6  | PlantUML Diagram                                                                                      | Raymond Ma                 | Feb 24   | Feb 25   | Done   |
| F7  | Produce Full Irregular Meshes                                                                         | Raymond + Rhea             | Feb 25   | Feb 25   | Done   |
| F8  | Mesh Relaxation                                                                                       | Raymond Ma                 | Feb 26   | Feb 26   | Done   |
| F9  | Compute neighbourhood relationships using Delaunay’s triangulation                                    | Gayan Athukorala           | Feb 27   | Feb 27   | Done   |
| F10 | Help Center with -H implementation CLI visualizer and generator                                       | Rhea + Raymond             | Feb 27   | Feb 27   | Done   |
| F11 | MVP Sandbox                                                                                           | Raymond Ma                 | Mar 20   | Mar 20   | Done   |
| F12 | Implement different Island Shapes                                                                     | Gayan Athukorala           | Mar 21   | Mar 22   | Done   |
| F13 | Select correct island shape Generator based on command line args                                      | Gayan Athukorala           | Mar 22   | Mar 22   | Done   |
| F14 | Create a generic altimetric profile builder                                                           | Raymond Ma                 | Mar 21   | Mar 21   | Done   |
| F15 | Implement Volcanos                                                                                    | Raymond Ma                 | Mar 22   | Mar 22   | Done   |
| F16 | Implement lakes inside of the island                                                                  | Rhea Gokhale               | Mar 22   | Mar 22   | Done   |
| F17 | Increase humidity to nearby land                                                                      | Rhea Gokhale               | Mar 22   | Mar 22   | Done   |
| F18 | Influence vegetation that grows near the lake                                                         | Rhea Gokhale               | Mar 23   | Mar 23   | Done   |
| F19 | Control Island attributes through command line                                                        | Gayan Athukorala           | Mar 22   | Mar 22   | Done   |
| F20 | Implement river segments in the island                                                                | Raymond Ma                 | Mar 23   | Mar 23   | Done   |
| F21 | Increase humidity to nearby land                                                                      | Rhea & Raymond             | Mar 22   | Mar 22   | Done   |
| F22 | Increase discharge level and thickness of rivers that merge                                           | Raymond Ma                 | Mar 23   | Mar 23   | Done   |
| F23 | Increase moisture to soils surrounding merged rivers                                                  | Raymond Ma                 | Mar 23   | Mar 23   | Done   |
| F24 | Create aquifer water body generator                                                                   | Rhea Gokhale               | Mar 22   | Mar 22   | Done   |
| F25 | Increase moisture to tiles surrounding the aquifer                                                    | Rhea Gokhale               | Mar 22   | Mar 22   | Done   |
| F26 | Abstract Soil Absorption Calculator                                                                   | Gayan Athukorala + Raymond | Mar 22   | Mar 23   | Done   |
| F27 | Abstract whittaker diagram builder                                                                    | Rhea Gokhale               | Mar 23   | Mar 24   | Done   |
| F28 | Assign seeds to attributes of randomly generated islands that can be inputted through command line    | Gayan Athukorala           | Mar 22   | Mar 24   | Done   |
| F29 | Tundra Biome                                                                                          | Rhea Gokhale & Raymond     | Mar 23   | Mar 24   | Done   |
| F30 | Forest Biome                                                                                          | Rhea Gokhale & Raymond     | Mar 23   | Mar 24   | Done   |
| F31 | Field Biome                                                                                           | Rhea Gokhale & Raymond     | Mar 23   | Mar 24   | Done   |
| F32 | User can enter any combination of integers and it will generate a reproducable seed                   | Gayan Athukorala           | Mar 23   | Mar 26   | Done   |
| F33 | Mangrove Biome                                                                                        | Rhea Gokhale   & Raymond   | Mar 23   | Mar 24   | Done   |
| F34 | Beach Biome                                                                                           | Rhea Gokhale  & Raymond    | Mar 23   | Mar 24   | Done   |
| F35 | HeatMaps                                                                                              | Gayan Athukorala           | Mar 25   | Mar 25   | Done   |
| F36 | Loading Bar on Generator                                                                              | Raymond Ma                 | Mar 26   | Mar 26   | Done   |
| F37 | Create Node Object                                                                                    | Gayan Athukorala           | April 5  | April 10 | Done   |
| F38 | Create Edge Object                                                                                    | Gayan Athukorala           | April 10 | April 11 | Done   |
| F39 | Interface for public contract of finding path between two nodes                                       | Gayan Athukorala           | April 11 | April 11 | Done   |
| F40 | Algorithm to find shortest path between two nodes                                                     | Gayan Athukorala           | April 11 | April 11 | Done   |
| F41 | Implement Graph ADT with IslandGen to find shortest path between two centroids on the island map      | Gayan Athukorala           | April 12 | April 12 | Done   |
| F42 | Cities appear on map with roads connecting them with number of cities controlled by command line args | Gayan Athukorala           | April 12 | April 12 | Done   |
| F43 | Each city on the map has a different size                                                             | Gayan Athukorala           | April 12 | April 12 | Done   |














