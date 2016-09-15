package br.rio.puc.piecewisebus.dao;

public interface QueryPostgis {

	String QUERY_ALL_DATE_TIME_WITH_DURATION = "select timeday, totaltime from generatorfunction where idedge = 1;";
	
	String QUERY_POINT_ROAD = "SELECT ro.gid, ST_AsText(st_makeline(st_linemerge(ro.geom))) "
			+ "FROM TABLE_NAME ro GROUP BY ro.gid ORDER BY ro.gid;";
	
	String QUERY_POINT_TAXI = "SELECT t.id, ST_AsText(t.point_geo), t.gid FROM view_taxi_close_linestring t";
	
	String QUERY_CLOSER_LINESTRING = "SELECT idEdge FROM grafoteste";
	
	String INSERT_PIECEWISE = "INSERT INTO piecewise VALUES (idedge, timeday, totaltime)";

}