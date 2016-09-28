#include "BinPacker.h"
#include<sstream>
using namespace std;

Container BinPacker::cTemplate;
mutex BinPacker::_mutex;
vector<thread> BinPacker::workers;


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
	Container::sAllowedMissing = allowedMissing*2;

	for (int i = 0; i < numTables; i++) {
		Table table;
		cin >> table.name;
		cin >> table.width;
		cin >> table.length;
		cin >> table.price;
		Container::sTables_all.push_back(table);
		table.rotate();
		Container::sTables_all.push_back(table);
	}

	Container c(Container::sTables_all);
	c.width = width;
	c.length = length;
	cTemplate = c;

	return true;
}


//bool BinPacker::start(){
//	clr;
//
//	Container::offsetX = 0;
//	Container::offsetY = 0;
//
//	int sz = Container::tables_all.size();
//	for (int i = 0; i < sz; i++) {
//		Container c = cTemplate;
//
//		c.addFirstTable(&Container::tables_all[i]);
//		if (c.runTables()) {
//			return true;
//		}
//	}
//
//	if (!Container::winners.empty()) {
//		int sz = Container::winners.size();
//		for (int i = 0; i < sz; i++) {
//			Container *c = &Container::winners[i];
//
//			c->printLayout();
//		}
//		return true;
//	}
//
//	return false;
//}

bool BinPacker::start() {
	pause;
	clr;

	Container::offsetX = 0;
	Container::offsetY = 0;

	int sz = Container::sTables_all.size();
	for (int i = 0; i < sz; i++) {
		thread t1(fWork, i);
		workers.push_back(move(t1));
	}

	for (int i = 0; i < sz; i++) {
		workers[i].join();
	}

	cout << "threads ended" << endl;
	pause;

	if (!Container::winners.empty()) {
		stringstream buffer;
		int sz = Container::winners.size();
		for (int i = 0; i < sz; i++) {
			Container *c = &Container::winners[i];

			buffer << c->printLayout();
		}
		Container::toClipboard(buffer.str());
		cout << "Result Copied To ClipBoard" << endl;

		return true;
	}

	return false;
}

bool BinPacker::fWork(int i) {
	cout << "start thread " << i << "\n";

	Container c = cTemplate;

	Table t = c.tables_all[i];

	c.addFirstTable(&t);
	if (c.runTables()) {
		cout << "thread " << i << " ended" << endl;
		return true;
	}
	cout << "thread " << i << " ended" << endl;
	return false;
}