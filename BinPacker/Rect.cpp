#include"Rect.h"
using namespace std;



Rect::Rect() {
	width = 0.0;
	length = 0.0;
}
Rect::~Rect(){}


bool Rect::overlaps(Rect &rect){
	if (rect.getRight() <= getLeft()) {
		return false;
	}
	else if (rect.getLeft() >= getRight()) {
		return false;
	}
	else if (rect.getTop() <= getBottom()) {
		return false;
	}
	else if (rect.getBottom() >= getTop()) {
		return false;
	}
	return true;
}
double Rect::getLeft(){
	return origin.x;
}
double Rect::getRight(){
	return origin.x + width;
}
double Rect::getTop(){
	return origin.y + length;
}
double Rect::getBottom(){
	return origin.y;
}


bool Rect::isEqualTo(Rect &rect) {
	if (rect.width == width && 
		rect.length == length &&
		rect.origin.isEqualTo(origin))
		return true;
	return false;
}


bool Rect::isInsideOf(Rect &rect){
	if (getTop() <= rect.getTop() &&
		getBottom() >= rect.getBottom()) {

		if (getLeft() >= rect.getLeft() &&
			getRight() <= rect.getRight()) {
			return true;
		}
	}
	return false;
}


bool Rect::rotate(){
	double newWidth = width;
	width = length;
	length = newWidth;
	return true;
}


bool Rect::surrounds(Point &point){
	if (point.x >= getLeft() &&
		point.x <= getRight()) {
//		cout << "true1" << endl;
		if (point.y >= getBottom() &&
			point.y <= getTop()) {
//			cout << "true2" << endl;
			return true;
		}
	}
	return false;
}
