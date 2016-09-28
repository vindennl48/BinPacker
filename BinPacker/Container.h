#pragma once
#include"NCLDS.h"
#include"Rect.h"
#include"Table.h"
#include<thread>
#include<mutex>


class Container :
	public Rect
{
public:
	Container();
	Container(vector<Table> all_tables);
	~Container();
	static mutex _mutex;
	static std::vector<Container> winners;
	static std::vector<Table> sTables_all;
//	static std::vector<double> maxPriceList;
	static int sAllowedMissing;
	int allowedMissing;
	std::vector<Table> tables_all;
	std::vector<Table> tables_left;
	std::vector<Table> tables_used;
	std::vector<Point> points;
	static double offsetX;
	static double offsetY;
	bool addFirstTable(Table *table);
	bool removeTable(Table *table);
	bool addPoints(Table *table);
	bool runTables();
	bool doesFit(Table *table);
	double getTotal();
	string printLayout();
	static void toClipboard(const std::string &s);
	Container getInst();
	static bool addToWinners(Container c);
};

