package com.littlenb.tools;

/***
 * 地图工具
 *
 * @author svili
 *
 */
public class GeoUtil {

    /**
     * 地球半径 ,单位:km
     */
    private static final double EARTH_RADIUS = 6378.137D;

    /**
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多,相差范围在0.2米以下</br>
     *
     * @param lng1 经度1,e.g. ddd.dddddd
     * @param lat1 纬度1,e.g. dd.dddddd
     * @param lng2 经度2
     * @param lat2 纬度2
     * @return 单位m
     */
    public static double distance(double lng1, double lat1, double lng2, double lat2) {
        /** 角度转为弧度 */
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        // 纬度差
        double a = radLat1 - radLat2;
        // 经度差
        double b = Math.toRadians(lng1) - Math.toRadians(lng2);
        // 计算公式
        double distance = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));

        distance = distance * EARTH_RADIUS * 1000;
        return distance;
    }

    /**
     * 获取指定范围内的坐标区域
     *
     * @param lng    经度,e.g. ddd.dddddd
     * @param lat    纬度,e.g. dd.dddddd
     * @param radius 距离,单位:m
     */
    public static CoordinateArea computeArea(double lng, double lat, double radius) {
        double degree = (24901 * 1609) / 360.0;

        double dpmLng = 1 / (degree * Math.cos(Math.toRadians(lat)));
        double radiusLng = dpmLng * radius;
        // 获取最小经度
        double minLng = lng - radiusLng;
        // 获取最大经度
        double maxLng = lng + radiusLng;

        double dpmLat = 1 / degree;
        double radiusLat = dpmLat * radius;
        // 获取最小纬度
        double minLat = lat - radiusLat;
        // 获取最大纬度
        double maxLat = lat + radiusLat;

        CoordinateArea area = new CoordinateArea();
        area.setMaxLatitude(maxLat);
        area.setMinLatitude(minLat);
        area.setMaxLongitude(maxLng);
        area.setMinLongitude(minLng);

        return area;
    }

    public static class CoordinateArea {
        /**
         * 经度
         */
        double minLongitude;

        double maxLongitude;

        /**
         * 纬度
         */
        double minLatitude;

        double maxLatitude;

        public double getMinLongitude() {
            return minLongitude;
        }

        public void setMinLongitude(double minLongitude) {
            this.minLongitude = minLongitude;
        }

        public double getMaxLongitude() {
            return maxLongitude;
        }

        public void setMaxLongitude(double maxLongitude) {
            this.maxLongitude = maxLongitude;
        }

        public double getMinLatitude() {
            return minLatitude;
        }

        public void setMinLatitude(double minLatitude) {
            this.minLatitude = minLatitude;
        }

        public double getMaxLatitude() {
            return maxLatitude;
        }

        public void setMaxLatitude(double maxLatitude) {
            this.maxLatitude = maxLatitude;
        }
    }

}
