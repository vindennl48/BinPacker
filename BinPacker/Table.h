#pragma once
#include"NCLDS.h"
#include"Rect.h"


class Table :
	public Rect
{
public:
	Table();
	~Table();
	std::string name;
	double price;
};

