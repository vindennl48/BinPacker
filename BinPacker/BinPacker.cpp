#include "BinPacker.h"
using namespace std;

Container BinPacker::cTemplate;


BinPacker::BinPacker(){}


BinPacker::~BinPacker(){}


bool BinPacker::getInput(){
	cout << "Add Table Information:" << endl;

	double width, length;
	cin >> width;
	cin >> length;

	int numTables;
	cin >> numTables;

	int allowedMissing;
	cin >> allowedMissing;

	for (int i = 0; i < numTables; i++) {
		Table table;
		cin >> table.name;
		cin >> table.width;
		cin >> table.length;
		cin >> table.price;
		Container::tables_all.push_back(table);
		table.rotate();
		Container::tables_all.push_back(table);
	}

	Container c;
	c.allowedMissing = allowedMissing*2;
	c.width = width;
	c.length = length;
	cTemplate = c;

	return true;
}


bool BinPacker::start(){
	clr;

	Container::offsetX = 0;
	Container::offsetY = 0;

	int sz = Container::tables_all.size();
	for (int i = 0; i < sz; i++) {
		Container c = cTemplate;

		c.addFirstTable(&Container::tables_all[i]);
		if (c.runTables()) {
			return true;
		}
	}

	if (!Container::winners.empty()) {
		int sz = Container::winners.size();
		for (int i = 0; i < sz; i++) {
			Container *c = &Container::winners[i];

			c->printLayout();
		}
		return true;
	}

	return false;
}