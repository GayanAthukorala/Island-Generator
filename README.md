# Assignment A2: Mesh Generator

  - Raymond Ma [mar47@mcmaster.ca]
  - Gayan Athukorala [athukorg@mcmaster.ca]
  - Rhea Gokhale [gokhaler@mcmaster.ca]

## How to run the product

_This section needs to be edited to reflect how the user can interact with thefeature released in your project_

### Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A2`

To install the different tooling on your computer, simply run:

```
mosser@azrael A2 % mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

### Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one single argument (so far), the name of the file where the generated mesh will be stored as binary.

```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar sample.mesh
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

### Visualizer

To visualize an existing mesh, go the the `visualizer` directory, and use `java -jar` to run the product. The product take two arguments (so far): the file containing the mesh, and the name of the file to store the visualization (as an SVG image).

```
mosser@azrael A2 % cd visualizer 
mosser@azrael visualizer % java -jar visualizer.jar ../generator/sample.mesh sample.svg

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```
To viualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tool slike `rsvg-convert`

## How to contribute to the project

When you develop features and enrich the product, remember that you have first to `package` (as in `mvn package`) it so that the `jar` file is re-generated by maven.

## Backlog

### Definition of Done

-- Feature Compiles without error and has meaningful contribution to MVP and does not interfere with the working status of previous features --

### Product Backlog


| Id  | Feature title                | Who?             | Start  | End    | Status |
|:---:|------------------------------|------------------|--------|--------|-------|
| F0  | Segment Generated            | Raymond Ma       | Feb 5  | Feb 9  | Done  |
| F1  | Segment Colour Added         | Gayan Athukorala | Feb 9  | Feb 9  | Done  |
| F2  | Segment Visualizer           | Rhea Gokhale     | Feb 9  | Feb 9  | Done  |
| F3  | Creating a mesh ADT          | Raymond Ma       | Feb 16 | Feb 16 | Done  |
| F4  | Producing full meshes        | Gayan Athukorala | Feb 17 | Feb 22 | Done  |
| F5  | Implementing Debug Mode      | Gayan + Rhea     | Feb 22 | Feb 24 | Done  |
| F6  | PlantUML Diagram             | Raymond Ma       | Feb 24 | Feb 25 | Done  |
| F7  | Produce Full Irregular Meshes | Raymond + Rhea   | Feb 25 | Feb 25 | Done  |
| F8  | Mesh Relaxation              | Raymond          | Feb 26 | Feb 26 | Done  |







