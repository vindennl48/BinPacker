#pragma once
#include"NCLDS.h"
#include"Point.h"

class Point;

class Rect
{
public:
	Rect();
	~Rect();
	double width;
	double length;
	Point origin;
	bool overlaps(Rect &rect);
	double getLeft();
	double getRight();
	double getTop();
	double getBottom();
	bool isEqualTo(Rect &rect);
	bool isInsideOf(Rect &rect);
	bool rotate();
	bool surrounds(Point &point);
};