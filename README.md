# GPS navigation mobile app running on Windows CE.
This is a digital navigation software on pocket pc. It helps people find their routes to a specific destination via mobile device.

## What is the Purpose ?
The aim of the project is to guide the user on a map of İstanbul by using a Pocket PC including a GPS receiver. 

## Features  
Searching point of interests and strets,
Drawing a route between two locations, 
Finding the nearest important point of interests  and  tracking of the users.


## How it works ?

> A* search algorithm which is a best-first, graph search algorithm that finds the least-cost path from a given initial node to one goal node.

> GPS data is read on COM 7  that contains NMEA data. The NMEA string is parsed and latitude and longitude values are get.

# Server

> The Guide Me Server reads polish map files then converts them into custom map format, creates the file to construct A* search graph, initializes A* search graph   and  creates a socket that listens to port 666.

## Future Work

> Map including the directions of the roads may be found.

> Drawing the map may be improved.

> Route finding algorithm may be improved and run on the client side.

> Traffic condition of the roads may be added.

## Project Structure

* `GuideMeClient/src` - folder for client code with static assets
* `GuideMeClient/src/RouteSearch` - folder for route search
* `GuiceMeServer/src` - folder for server codes

For detailed explanation on how things work, see the keynote presentation `GPS NAVIGATION USING POCKET PC.key`

### Authors:
  
**Hakan Gurses**
  
**Sahin Habesoglu**
