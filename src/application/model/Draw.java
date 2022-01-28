package application.model;

import java.io.File;
import java.util.ArrayList;


import application.model.algorithm.ShortestPath;
import application.model.map.Area;
import application.model.map.Building;
import application.model.map.highway.*;
import application.model.map.highway.line.*;
import application.model.map.highway.path.*;
import application.model.map.landuse.Landuse;
import application.model.map.leisure.*;
import application.model.map.natural.*;
import application.model.map.water.WaterRiver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/*
 * @Author: Yingqi LUO
 */

public class Draw {
	
//	@FXML
//	private static Canvas canvas;
	
	public static double widthCanvas;
	public static double heightCanvas;
	public static double sizeMapLon;
	public static double sizeMapLat;
	
	public static boolean isSearched;
	public static long idNodeFrom;
	public static long idNodeTo;
	
	public static ImageView fromImg;
	public static ImageView toImg;
	
	
	
	
	public Draw() {
		
	}
	
	
	public static void initDonnee(double width, double height) {
		Draw.widthCanvas = width;
		Draw.heightCanvas = height;
		Draw.sizeMapLat = OpenStreetMap.maxLat - OpenStreetMap.minLat;		// + 0.002 (there are somme streets that exceed the canvas)
		Draw.sizeMapLon = OpenStreetMap.maxLon - OpenStreetMap.minLon;
		
		
	}
	

//	---------- draw way -------------
	public static void drawWay(GraphicsContext gc, Way way) {
		double X[] = new double[way.nodeList.size()];
		double Y[] = new double[way.nodeList.size()];
		double x, y;
		for(int i=0; i<way.nodeList.size(); i++) {
			x = coordinateXCanvas(OpenStreetMap.nodeList.get(way.nodeList.get(i)));
			y = coordinateYCanvas(OpenStreetMap.nodeList.get(way.nodeList.get(i)));
			X[i] = x;
			Y[i] = y;
		}
		gc.setLineWidth(1);
		gc.setStroke(Color.GRAY);
		gc.strokePolyline(X, Y, X.length);
		gc.setLineWidth(1.0);
	}
	
	
	public static double coordinateXCanvas(Node node) {
		return (node.getLon() - OpenStreetMap.minLon) * Draw.widthCanvas / Draw.sizeMapLon;
	}
	
	public static double coordinateYCanvas(Node node) {
//		return Math.abs(((node.getLat() - OpenStreetMap.minLat) * Draw.heightCanvas / Draw.sizeMapLat)-Draw.heightCanvas);
		return Draw.heightCanvas - ((node.getLat() - OpenStreetMap.minLat) * Draw.heightCanvas / Draw.sizeMapLat);
	}
	
	
	public static void drawMap(GraphicsContext gc, AnchorPane root) {
		
		for(Way way: OpenStreetMap.wayList.values()) {
//			Draw.drawWay(gc, way);
			double[] X = new double[way.nodeList.size()];
			double[] Y = new double[way.nodeList.size()];
			for(int i=0; i < way.nodeList.size(); i++) {
//				if(OpenStreetMap.minLat<OpenStreetMap.nodeList.get(way.nodeList.get(i)).getLat()<OpenStreetMap.)
				double x = coordinateXCanvas(OpenStreetMap.nodeList.get(way.nodeList.get(i)));
				double y = coordinateYCanvas(OpenStreetMap.nodeList.get(way.nodeList.get(i)));
				X[i] = x;
				Y[i] = y;
			}
			
			/**
			 * draw the map
			 */
			for(int i=way.tagList.size()-1; i>=0; i--) {
				String name = "";
				if(way.tagList.get(i).getKey().indexOf("name") == 0) {
					name = way.tagList.get(i).getValue();
				}
				if(way.tagList.get(i).getKey().indexOf("landuse") == 0) {
					Draw.drawLanduse(gc, way.tagList.get(i), way, name, X, Y);
				}
				if(way.tagList.get(i).getKey().indexOf("natural") == 0) {
					Draw.drawNatural(gc, way.tagList.get(i), way, name,  X, Y);
				}
				if(way.tagList.get(i).getKey().indexOf("building") == 0) {					
					Draw.drawBuilding(gc, way, name,  X, Y);
				}
				if(way.tagList.get(i).getKey().indexOf("highway") == 0) {
					Draw.drawHighway(gc, way.tagList.get(i), way, name,  X, Y);
				}
				if(way.tagList.get(i).getKey().indexOf("leisure") == 0) {
					Draw.drawLeisure(gc, way.tagList.get(i), way, name,  X, Y);
				}
				
			}
		}
		for(Node node: OpenStreetMap.nodeList.values()) {
			for(Tag tag: node.tagList) {
				if(tag.getKey().indexOf("natural") == 0) {
					if(tag.getValue().indexOf("tree") == 0) {
						double x, y;
						x = (node.getLon() - OpenStreetMap.minLon) * Draw.widthCanvas / Draw.sizeMapLon;
						y = Math.abs(((node.getLat() - OpenStreetMap.minLat) * Draw.heightCanvas / Draw.sizeMapLat)-Draw.heightCanvas);
						/**
						 * Draw each unit tree
						 */
						Draw.drawUnitNode(gc, node, x, y, "tree");
					}
				}
			}
		}
		
		
		/*
		 * Draw the shortest path
		 */
//		long idFrom = 33806077L;
//		long idTo = 33806094L;
		if(Draw.isSearched && Draw.idNodeFrom!=0 && Draw.idNodeTo!=0) {			
//			boolean imgExist = false;
			if(Draw.fromImg != null) {				
				Draw.fromImg.setImage(null);
			}
			if(Draw.toImg != null) {
				Draw.toImg.setImage(null);
			}
			File fileTo = new File("src\\application\\img\\from.png");
			File fileFrom = new File("src\\application\\img\\to.png");
			Image imageFrom = new Image(fileFrom.toURI().toString());
			Image imageTo = new Image(fileTo.toURI().toString());
			Draw.fromImg = new ImageView();
			Draw.toImg = new ImageView();
			Draw.fromImg.setImage(imageFrom);
			Draw.toImg.setImage(imageTo);
			Draw.fromImg.setFitWidth(20);
			Draw.toImg.setFitWidth(20);
			Draw.fromImg.setFitHeight(25);
			Draw.toImg.setFitHeight(25);
			
			ShortestPath shortestPathAlgo = new ShortestPath(Draw.idNodeFrom, Draw.idNodeTo);
			ArrayList<NodePath> passedShortestPath = shortestPathAlgo.algoGetShortestPath();
			ArrayList<NodePath> shortestPath = shortestPathAlgo.getShortesPath();
			double Xpath[] = new double[shortestPath.size()];
			double Ypath[] = new double[shortestPath.size()];
			for(int i=0; i<shortestPath.size(); i++) {
//				System.out.println(shortestPath.get(i).getId());
//				System.out.println(shortestPath.get(i).getLon()+","+shortestPath.get(i).getLat());
				double x = coordinateXCanvas(shortestPath.get(i));
				double y = coordinateYCanvas(shortestPath.get(i));
				Xpath[i] = x;
				Ypath[i] = y;
			}
//			root.setLeftAnchor(Draw.fromImg, 300.0);
			if(Xpath.length > 0) {				
				double xImgFrom = Xpath[0] + 293;
				double yImgFrom = Ypath[0] - 15;
				double xImgTo = Xpath[shortestPath.size()-1] + 293;
				double yImgTo = Ypath[shortestPath.size()-1] - 15;
				Draw.fromImg.setLayoutX(xImgFrom);
				Draw.fromImg.setLayoutY(yImgFrom);
				Draw.toImg.setLayoutX(xImgTo);
				Draw.toImg.setLayoutY(yImgTo);
				root.getChildren().add(Draw.fromImg);
				root.getChildren().add(Draw.toImg);
				gc.setLineWidth(5);
				gc.setStroke(Color.GREEN);
				gc.strokePolyline(Xpath, Ypath, Xpath.length);
				gc.setLineWidth(1.0);
			}
			else {
//				System.out.println("no way to go");
			}
		}

		
	}
	
	
	public static void drawUnitNode(GraphicsContext gc, Node node, double x, double y, String type) {
		if(type == "tree") {
			NaturalTrees tree = new NaturalTrees(node.getId());
			gc.setFill(Color.web(tree.color));
			gc.fillOval(x-2.5, y-2.5, 5, 5);
			gc.setFill(Color.BROWN);
			gc.fillOval(x-0.25, y-0.25, 0.5, 0.5);
		}
		if(type == "water") {
			NaturalWater water = new NaturalWater(node.getId());
			gc.setFill(Color.web(water.color));
			gc.fillOval(x-2.5, y-2.5, 5, 5);
		}
	}
	
	public static void drawArea(GraphicsContext gc, double[] X, double[] Y, Area area, Way way) {
		gc.setStroke(Color.web(area.colorBord));
		gc.strokePolyline(X, Y, X.length);
		gc.setFill(Color.web(area.color));
		gc.fillPolygon(X, Y, way.nodeList.size());
	}
	
	private static double getXcoef (Area area, double angle) {
		if(angle > 90) {
			angle = 90 - (180 - angle);
		}
		return area.width * Math.cos(angle);
	}
	
	private static double getYcoef (Area area, double angle) {
		if(angle > 90) {
			angle = 90 - (180 - angle);
		}
		return area.width * Math.sin(angle);
	}
	
	private static void methodeDrawPath(GraphicsContext gc, Way way,  Area area , double[] X, double[] Y, double angle) {	
		//TODO: faut bien pour conditionne pour 2 cas de sens(vertical, horizon) differents, 
		//vu qu'il sont 2 'way' different et fermer, ca change le taill de tous les rue pour sens vertical et horizontal
		double[] Xp_fill_left = new double[X.length];
		double[] Yp_fill_left = new double[X.length];
			
		double[] Xp_fill_right = new double[X.length];
		double[] Yp_fill_right = new double[X.length];
		
		double[] Xp = new double[2 * X.length];
		double[] Yp = new double[2 * Y.length];
		int tmp = X.length;
		double xcoef = getXcoef(area, angle);
		double ycoef = getYcoef(area, angle);
		if(angle < 90) {			
			for(int i=0; i<X.length; i++) {
				Xp[i] = X[i] - xcoef;
				Yp[i] = Y[i] + ycoef;			
				Xp_fill_left[i] = X[i] - xcoef;
				Yp_fill_left[i] = Y[i] + ycoef;
				
				Xp_fill_right[i] = X[i] + xcoef;
				Yp_fill_right[i] = Y[i] - ycoef;			
				
				Xp[tmp] = X[X.length-1-i] + xcoef;
				Yp[tmp] = Y[Y.length-1-i] - ycoef;
				
				tmp++;
			}
			Xp[Xp.length-1] = X[0] + xcoef;
			Yp[Yp.length-1] = Y[0] - ycoef;		
		}
		else {
			for(int i=0; i<X.length; i++) {
				Xp[i] = X[i] - xcoef;
				Yp[i] = Y[i] - ycoef;			
				Xp_fill_left[i] = X[i] - xcoef;
				Yp_fill_left[i] = Y[i] - ycoef;
				
				Xp_fill_right[i] = X[i] + xcoef;
				Yp_fill_right[i] = Y[i] + ycoef;			
				
				Xp[tmp] = X[X.length-1-i] + xcoef;
				Yp[tmp] = Y[Y.length-1-i] + ycoef;
				
				tmp++;
			}
			Xp[Xp.length-1] = X[0] + xcoef;
			Yp[Yp.length-1] = Y[0] + ycoef;	
		}
		gc.setStroke(Color.web(area.colorBord));
		gc.strokePolyline(Xp_fill_left, Yp_fill_left, Xp_fill_left.length);
		gc.strokePolyline(Xp_fill_right, Yp_fill_right, Xp_fill_right.length);
		gc.setFill(Color.web(area.color));
		gc.fillPolygon(Xp, Yp, way.nodeList.size() * 2);
	}
	
	private static void methodeDrawLine(GraphicsContext gc,  HighwayLine highway , double[] X, double[] Y) {
		gc.setLineWidth(highway.width);
		gc.setLineDashes(highway.sizeDash );
		gc.setStroke(Color.web(highway.color));
		gc.strokePolyline(X, Y, X.length);
		gc.setLineDashes(null);
		gc.setLineWidth(1.0);
	}
	
	public static void drawLanduse(GraphicsContext gc, Tag tag,  Way way, String name, double[] X, double[] Y) {
//		Landuse landuse = new Landuse(way.getId(), name);
		Landuse landuse;
		if(tag.getValue().indexOf("garages ") == 0) {
			landuse = new Landuse(way.getId(), name, Colors.Landuse, Colors.Landuse);
		}
//		Draw.drawArea(gc, X, Y, landuse, way);
	}
	
	/**
	 * color all building
	 */
	private static void drawBuilding(GraphicsContext gc, Way way, String name, double[] X, double[] Y) {
		if(name.length() == 0) {	
			Building building = new Building(way.getId());
			gc.setStroke(Color.web(building.colorBord));
			gc.strokePolyline(X, Y, X.length);
			gc.setFill(Color.web(building.color));
			gc.fillPolygon(X, Y, way.nodeList.size());
		}
		else {
			Building building = new Building(way.getId(), name);
			gc.setStroke(Color.web(building.colorBord));
			gc.strokePolyline(X, Y, X.length);
			gc.setFill(Color.web(building.color));
			gc.fillPolygon(X, Y, way.nodeList.size());
		}
	}
	
	/**
	 * Color all of the highway
	 */
	private static void drawHighway(GraphicsContext gc, Tag tag, Way way, String name, double[] X, double[] Y) {
		//TODO: recuperer le nom du chemin qui est dans list de tag
		/**
		 * Path Roads
		 */
		if(tag.getValue().indexOf("motorway") == 0) {	
			HighwayMotorway highwayMotorway = new HighwayMotorway(way.getId(), name);
			double coef = highwayMotorway.getCoef();
			Draw.methodeDrawPath(gc, way, highwayMotorway, X, Y, coef);
		}
		else if(tag.getValue().indexOf("trunk") == 0) {	
			HighwayTrunk highwayTrunk = new HighwayTrunk(way.getId(), name);
			double coef = highwayTrunk.getCoef();
			Draw.methodeDrawPath(gc, way, highwayTrunk, X, Y, coef);
		}
		// highway primary
		else if(tag.getValue().indexOf("primary") == 0) {	
			HighwayPrimary highwayPrimary = new HighwayPrimary(way.getId(), name);
			double coef = highwayPrimary.getCoef();
			Draw.methodeDrawPath(gc, way, highwayPrimary, X, Y, coef);
		}
		else if(tag.getValue().indexOf("secondary") == 0) {	
			HighwaySecondary highwaySecondary = new HighwaySecondary(way.getId(), name);
			double coef = highwaySecondary.getCoef();
			Draw.methodeDrawPath(gc, way, highwaySecondary, X, Y, coef);
		}
		else if(tag.getValue().indexOf("tertiary") == 0) {	
			HighwayTertiary highwayTertiary = new HighwayTertiary(way.getId(), name);
			double coef = highwayTertiary.getCoef();
			Draw.methodeDrawPath(gc, way, highwayTertiary, X, Y, coef);
		}
		else if(tag.getValue().indexOf("unclassified") == 0) {
			HighwayUnclassified highwayUnclassified = new HighwayUnclassified(way.getId(), name);
			double coef = highwayUnclassified.getCoef();
			Draw.methodeDrawPath(gc, way, highwayUnclassified, X, Y, coef);
		}
		else if(tag.getValue().indexOf("residential") == 0) {
			HighwayResidential highwayResidential = new HighwayResidential(way.getId(), name);
			double coef = highwayResidential.getCoef();
			Draw.methodeDrawPath(gc, way, highwayResidential, X, Y, coef);
		}
		/**
		 * Link roads
		 */
		else if(tag.getValue().indexOf("motorway_link") == 0) {	
			HighwayMotorwayLink highwayMotorwayLink = new HighwayMotorwayLink(way.getId(), name);
			double coef = highwayMotorwayLink.getCoef();
			Draw.methodeDrawPath(gc, way, highwayMotorwayLink, X, Y, coef);
		}
		else if(tag.getValue().indexOf("trunk_link") == 0) {	
			HighwayTrunkLink highwayTrunkLink = new HighwayTrunkLink(way.getId(), name);
			double coef = highwayTrunkLink.getCoef();
			Draw.methodeDrawPath(gc, way, highwayTrunkLink, X, Y, coef);
		}
		else if(tag.getValue().indexOf("primary_link") == 0) {	
			HighwayPrimaryLink highwayPrimaryLink = new HighwayPrimaryLink(way.getId(), name);
			double coef = highwayPrimaryLink.getCoef();
			Draw.methodeDrawPath(gc, way, highwayPrimaryLink, X, Y, coef);
		}
		else if(tag.getValue().indexOf("secondary_link") == 0) {	
			HighwaySecondaryLink highwaySecondaryLink = new HighwaySecondaryLink(way.getId(), name);
			double coef = highwaySecondaryLink.getCoef();
			Draw.methodeDrawPath(gc, way, highwaySecondaryLink, X, Y, coef);
		}
		else if(tag.getValue().indexOf("tertiary_link") == 0) {	
			HighwayTertiaryLink highwayTertiaryLink = new HighwayTertiaryLink(way.getId(), name);
			double coef = highwayTertiaryLink.getCoef();
			Draw.methodeDrawPath(gc, way, highwayTertiaryLink, X, Y, coef);
		}
		/**
		 * Special road types
		 */
		else if(tag.getValue().indexOf("living_street") == 0) {	
			HighwayLivingStreet highwayLivingStreet = new HighwayLivingStreet(way.getId(), name);
			double coef = highwayLivingStreet.getCoef();
			Draw.methodeDrawPath(gc, way, highwayLivingStreet, X, Y, coef);
		}
		else if(tag.getValue().indexOf("service") == 0) {
			HighwayService highwayService = new HighwayService(way.getId(), name);
			double coef = highwayService.getCoef();
			Draw.methodeDrawPath(gc, way, highwayService, X, Y, coef); 
		}
		else if(tag.getValue().indexOf("pedestrian") == 0) {
			HighwayPedestrian highwayPedestrian = new HighwayPedestrian(way.getId(), name);
			double coef = highwayPedestrian.getCoef();
			Draw.methodeDrawPath(gc, way, highwayPedestrian, X, Y, coef);
		}
		else if(tag.getValue().indexOf("track") == 0) {
			HighwayTrack highwayTrack = new HighwayTrack(way.getId(), name);
			Draw.methodeDrawLine(gc, highwayTrack, X, Y); 
		}
		else if(tag.getValue().indexOf("bus_guideway") == 0) {
			gc.setLineWidth(3);
			gc.setStroke(Color.rgb(102, 102, 255));
			gc.strokePolyline(X, Y, X.length);
			HighwayBusGuideway highwayBusGuideway = new HighwayBusGuideway(way.getId(), name);
			Draw.methodeDrawLine(gc, highwayBusGuideway, X, Y); 
		}
		else if(tag.getValue().indexOf("footway") == 0) {
			HighwayFootway highwayFootway = new HighwayFootway(way.getId(), name);
			Draw.methodeDrawLine(gc, highwayFootway, X, Y); 
		}
		else if(tag.getValue().indexOf("steps") == 0) {
			HighwaySteps highwaySteps = new HighwaySteps(way.getId(), name);
			Draw.methodeDrawLine(gc, highwaySteps, X, Y); 
		}
	}

	
	
	public static void drawNatural(GraphicsContext gc, Tag tag, Way way, String name, double[] X, double[] Y) {
		if(tag.getValue().indexOf("tree_row") == 0) {
			NaturalTrees tree_row = new NaturalTrees(way.getId(), name, 2);
			Draw.methodeDrawPath(gc, way, tree_row, X, Y, 0);
		}
		if(tag.getValue().indexOf("scrub") == 0) {
			NaturalTrees scrub = new NaturalTrees(way.getId(), name);
			Draw.drawArea(gc, X, Y, scrub, way);
		}
		if(tag.getValue().indexOf("heath") == 0) {
			NaturalHeath heath = new NaturalHeath(way.getId(), name);
			Draw.drawArea(gc, X, Y, heath, way);
		}
		if(tag.getValue().indexOf("grassland") == 0) {
			NaturalGrassland grassland = new NaturalGrassland(way.getId(), name);
			Draw.drawArea(gc, X, Y, grassland, way);
		}
		if((tag.getValue().indexOf("bare_rock") == 0) || (tag.getValue().indexOf("scree") == 0) || (tag.getValue().indexOf("shingle") == 0) ) {
			NaturalRocks bare_rock = new NaturalRocks(way.getId(), name);
			Draw.drawArea(gc, X, Y, bare_rock, way);
		}
		if(tag.getValue().indexOf("water") == 0) {
			NaturalWater water = new NaturalWater(way.getId(), name);
			Draw.drawArea(gc, X, Y, water, way);
		}
		if(tag.getValue().indexOf("wetland") == 0) {
			NaturalWetland wetland = new NaturalWetland(way.getId(), name);
			Draw.drawArea(gc, X, Y, wetland, way);
		}
		if(tag.getValue().indexOf("bay") == 0) {
			NaturalBay bay = new NaturalBay(way.getId(), name);
			Draw.drawArea(gc, X, Y, bay, way);
		}
		if(tag.getValue().indexOf("strait") == 0) {
			NaturalStrait strait = new NaturalStrait(way.getId(), name);
			Draw.drawArea(gc, X, Y, strait, way);
		}
		if(tag.getValue().indexOf("beach") == 0) {
			NaturalBeach beach = new NaturalBeach(way.getId(), name);
			Draw.drawArea(gc, X, Y, beach, way);
		}
		if(tag.getValue().indexOf("coastline") == 0) {
			if(way.nodeList.get(0).equals(way.nodeList.get(way.nodeList.size()-1))) {				
				NaturalCoastline coastline = new NaturalCoastline(way.getId(), name);
				Draw.drawArea(gc, X, Y, coastline, way);
			}
//			else {
//				// TODO: colorer la mer non fermer
//				if(OpenStreetMap.nodeList.get(way.nodeList.get(0)).getLon() == OpenStreetMap.minLon) {
//					
//				}
//				if(OpenStreetMap.nodeList.get(way.nodeList.get(0)).getLat() == OpenStreetMap.minLat) {
//					
//				}
//				if(OpenStreetMap.nodeList.get(way.nodeList.get(way.nodeList.size()-1)).getLat() == OpenStreetMap.maxLat) {
//					
//				}
//				if(OpenStreetMap.nodeList.get(way.nodeList.get(way.nodeList.size()-1)).getLon() == OpenStreetMap.maxLon) {
//					
//				}
//			}
//			else {
//				if(Math.abs(X[0]-Draw.widthCanvas) > Draw.widthCanvas/2) {	// plus proche a gauch du fenetre
//					if(Math.abs(Y[0]-Draw.heightCanvas) > Draw.heightCanvas/2) {	// plus proche en haut du fenetre
//						double[] Xwater = new double[X.length+2];
//						double[] Ywater = new double[Y.length+2];
//						Xwater[0] = 0;
//						Ywater[0] = 0;
//						Xwater[Xwater.length-1] = 0;
//						Ywater[Ywater.length-1] = 0;
//						for(int i=1; i<Xwater.length-1; i++) {
//							Xwater[i] = X[i-1];
//							Ywater[i] = Y[i-1];
//						}
//						NaturalCoastline coastline = new NaturalCoastline(way.getId(), name);
//						Draw.drawArea(gc, Xwater, Ywater, coastline, way);
//					}
//					else {
//						double[] Xwater = new double[X.length+2];
//						double[] Ywater = new double[Y.length+2];
//						Xwater[0] = 0;
//						Ywater[0] = Draw.heightCanvas;
//						Xwater[Xwater.length-1] = 0;
//						Ywater[Ywater.length-1] = Draw.heightCanvas;
//						for(int i=1; i<Xwater.length-1; i++) {
//							Xwater[i] = X[i-1];
//							Ywater[i] = Y[i-1];
//						}
//						NaturalCoastline coastline = new NaturalCoastline(way.getId(), name);
//						Draw.drawArea(gc, Xwater, Ywater, coastline, way);
//					}
//				}
//				else {
//					if(Math.abs(Y[0]-Draw.heightCanvas) > Draw.heightCanvas/2) {	// plus proche en haut du fenetre
//						double[] Xwater = new double[X.length+2];
//						double[] Ywater = new double[Y.length+2];
//						Xwater[0] = Draw.widthCanvas;
//						Ywater[0] = 0;
//						Xwater[Xwater.length-1] = Draw.widthCanvas;
//						Ywater[Ywater.length-1] = 0;
//						for(int i=1; i<Xwater.length-1; i++) {
//							Xwater[i] = X[i-1];
//							Ywater[i] = Y[i-1];
//						}
//						NaturalCoastline coastline = new NaturalCoastline(way.getId(), name);
//						Draw.drawArea(gc, Xwater, Ywater, coastline, way);
//					}
//					else {
//						double[] Xwater = new double[X.length+2];
//						double[] Ywater = new double[Y.length+2];
//						Xwater[0] = Draw.widthCanvas;
//						Ywater[0] = Draw.heightCanvas;
//						Xwater[Xwater.length-1] = Draw.widthCanvas;
//						Ywater[Ywater.length-1] = Draw.heightCanvas;
//						for(int i=1; i<Xwater.length-1; i++) {
//							Xwater[i] = X[i-1];
//							Ywater[i] = Y[i-1];
//						}
//						NaturalCoastline coastline = new NaturalCoastline(way.getId(), name);
//						Draw.drawArea(gc, Xwater, Ywater, coastline, way);
//					}
//				}
//			}
		}
	}
	
	public static void drawLeisure(GraphicsContext gc, Tag tag, Way way, String name, double[] X, double[] Y) {
		if(tag.getValue().indexOf("dog_park") == 0) {
			LeisureDogpark dog_park = new LeisureDogpark(way.getId(), name);
			Draw.drawArea(gc, X, Y, dog_park, way);
		}
		if(tag.getValue().indexOf("park") == 0) {
			LeisurePark park = new LeisurePark(way.getId(), name);
			Draw.drawArea(gc, X, Y, park, way);
		}
	}
	
	public static void drawWater(GraphicsContext gc, Tag tag, Way way, String name, double[] X, double[] Y) {
		if(tag.getValue().indexOf("river") == 0) {
			WaterRiver river = new WaterRiver(way.getId(), name);
//			Draw.drawArea(gc, X, Y, river, way);
			Draw.methodeDrawPath(gc, way, river, X, Y, heightCanvas);
		}
	}
	
}
