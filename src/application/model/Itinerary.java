package application.model;

import java.util.HashMap;


/**
 * 
 * @author Yingqi LUO
 *
 */
public class Itinerary {
	
	private String numFrom;
	private String from;
	private String numTo;
	private String to;
	private long idFrom;
	private long idTo;
	
	
	
	public Itinerary(String numFrom, String from, String numTo, String to) {
		
		this.numFrom = numFrom;
		this.from = from;
		this.numTo = numTo;
		this.to = to;
		
//		System.out.println(numFrom);
//		System.out.println(numFrom.length());
		
//		System.out.println("103".length());
		
//		StreetAddr.nodeList
//		StreetAddr.wayList
		
//		System.out.println("1-3".length());
//		System.out.println("63-65".length());
//		System.out.println("231-255".length());
		
		System.out.println("id node in highway from: " + this.getIdHighway(numFrom, from));
		System.out.println("id node in highway to:" + this.getIdHighway(numTo, to));
		
//		System.out.println(this.getIdAddrWithNum(numTo, to));
		
	}
	
	public boolean hasNumStreet(String numAddr) {
		if(numAddr.length() == 0) {
			return false;
		}
		return true;
	}
	
	public String uperCase(String str) {
		char[] cs;
		cs=str.toCharArray();
		cs[0]-=32;
		return String.valueOf(cs);
	}
	
	public long getIdHighway(String numAddr, String addr) {	
		boolean tagHighway = false;
		boolean hasway = true;
		long idNode = 0;
		for(Way way: OpenStreetMap.wayList.values()) {
			for(int i=0; i<way.tagList.size(); i++) {
				
				if(way.tagList.get(i).getKey().indexOf("highway") == 0) {
					tagHighway = true;
				}
				if(tagHighway) {
					if(way.tagList.get(i).getKey().indexOf("name") == 0) {
						if(way.tagList.get(i).getValue().indexOf(addr) == 0) {
//							System.out.println(way.tagList.get(i).getValue());
							if(this.hasNumStreet(numAddr)) {
//								System.out.println("okkkkkk");
								int item = 0;
								long idAddr = this.getIdAddrWithNum(numAddr, addr);
								System.out.println("id addr: " + idAddr);
								double minDistance = this.getDistance(idAddr, way.nodeList.get(item));
								for(int j=0; j<way.nodeList.size(); j++) {
									double distance = this.getDistance(idAddr, way.nodeList.get(j));
									if(distance < minDistance) {
										minDistance = distance;
										item = j;
									}
								}
								idNode = way.nodeList.get(item);
								return idNode;
//								return way.nodeList.get(item);
							}
							else {
//								System.out.println("ok");
//								System.out.println(way.nodeList.size());
								int indexNode = way.nodeList.size()/2;
//								System.out.println(indexNode);
//								System.out.println(way.nodeList.get(indexNode));
//								return way.nodeList.get(indexNode);
								idNode = way.nodeList.get(indexNode);
								return idNode;
							}
						}
					}
				}
			}
		}
		
		return idNode;
	}
	
	public double getDistance(long idFrom, long idTo) {
		return Math.pow(Math.sqrt(OpenStreetMap.nodeList.get(idTo).getLat()-OpenStreetMap.nodeList.get(idFrom).getLat())
				+Math.sqrt(OpenStreetMap.nodeList.get(idTo).getLon()-OpenStreetMap.nodeList.get(idFrom).getLon()), 1.0/2);
	}
	
	public Long getIdAddrWithNum(String numAddr, String addr) {
		
//		HashMap<Long, String> tagAddr = new HashMap();
//		String tagAddr;
		
		boolean boolAddrWay = false;
		boolean boolAddrNode = false;
		
		for(Way way: OpenStreetMap.wayList.values()) {
			for(int i=way.tagList.size()-1; i>-1; i--) {
				if(way.tagList.get(i).getKey().indexOf("addr:street") == 0) {
//					System.out.println(addr+" -> "+way.tagList.get(i).getValue());
					if(way.tagList.get(i).getValue().indexOf(addr) == 0) {
//						System.out.println("ok1");
						boolAddrWay = true;
					}
				}
				if(boolAddrWay) {					
					 if(way.tagList.get(i).getKey().indexOf("addr:housenumber") == 0) {
						if(way.tagList.get(i).getValue().length() > 5) {
//							System.out.println(way.tagList.get(i).getValue());
//							System.out.println("length 5?");
							String[] temp = new String[3];
							String delimeter = " ";
							int num1=0, num2=0, numTemp;
							numTemp = Integer.valueOf(numAddr);
							String num = way.tagList.get(i).getValue();
							temp = num.split(delimeter);
							if(temp[1] == "-") {								
								num1 = Integer.valueOf(temp[0]);
								num2 = Integer.valueOf(temp[2]);			
							}
							if(numTemp > num1 && numTemp < num2) {
//								tagAddr = String.valueOf(way.getId());
								int indexNode = way.nodeList.size()/2;
								return way.nodeList.get(indexNode);
							}
						}
						else {
							if(way.tagList.get(i).getValue().indexOf(numAddr) == 0) {
//								tagAddr = String.valueOf(way.getId());
//								return tagAddr;
								int indexNode = way.nodeList.size()/2;
								return way.nodeList.get(indexNode);
							}
						}
					}
				}
				
			}
		}
	
		for(Node node: OpenStreetMap.nodeList.values()) {
			for(int i=node.tagList.size()-1; i>-1; i--) {
				if(node.tagList.get(i).getKey().indexOf("addr:street") == 0) {
//					System.out.println(addr+" -> "+node.tagList.get(i).getValue());
					if(node.tagList.get(i).getValue().indexOf(addr) == 0) {
//						System.out.println("ok1");
						boolAddrNode = true;
					}
				}
				else if(node.tagList.get(i).getKey().indexOf("addr:housenumber") == 0) {
					if(boolAddrNode) {						
						if(node.tagList.get(i).getValue().length() > 5) {
//							System.out.println(node.tagList.get(i).getValue());
							String temp[] = new String[3];
							int num1=0, num2=0, numTemp;
							numTemp = Integer.valueOf(numAddr);
							String num = node.tagList.get(i).getValue();
							temp = num.split(" ");
							if(temp[1] == "-") {								
								num1 = Integer.valueOf(temp[0]);
								num2 = Integer.valueOf(temp[2]);			
							}
							if(numTemp > num1 && numTemp < num2) {
//								tagAddr[0] = String.valueOf(node.getId());
//								tagAddr[1] = "node";
								return node.getId();
							}
						}
						else {
							if(node.tagList.get(i).getValue().indexOf(numAddr) == 0) {
//								return node.getId();
//								tagAddr.put(node.getId(), delimeter)
//								tagAddr[0] = String.valueOf(node.getId());
//								tagAddr[1] = "node";
								return node.getId();
							}
						}
					}
				}
				
			}
		}

		
		return null;
		
	}

}
