#pragma once
#include"NCLDS.h"

class Point
{
public:
	double x;
	double y;
	Point();
	Point(double x, double y);
	~Point();
	bool isEqualTo(Point &point);
//	bool isInsideOf(Rect &rect);
};