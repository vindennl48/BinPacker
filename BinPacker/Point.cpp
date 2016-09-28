#include "Point.h"
using namespace std;



Point::Point() {
	x = 0.0;
	y = 0.0;
}
Point::Point(double x, double y) {
	this->x = x;
	this->y = y;
}
Point::~Point() {}



bool Point::isEqualTo(Point &point){
	if (point.x == x && point.y == y)
		return true;
	return false;
}


//bool Point::isInsideOf(Rect &rect){
//	if (x >= rect.getLeft() &&
//		x <= rect.getRight()) {
//		if (y >= rect.getBottom() &&
//			y <= rect.getTop()) {
//			return true;
//		}
//	}
//	return false;
//}
