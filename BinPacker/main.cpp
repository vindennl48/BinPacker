
#include"NCLDS.h"
#include"BinPacker.h"
#include"Container.h"
#include"Table.h"
using namespace std;

int main() {

	BinPacker::getInput();
	cout << "Collected Data, Starting Program" << endl;
	BinPacker::start();
	cout << "Program Ended" << endl;

	//Table table;
	//Point point;

	////point.x = 0.0000;
	////point.y = 55.9085;

	//point.x = 0.0000;
	//point.y = 0.0000;

	//table.name = "7550GAL";
	//table.width = 29.9228;
	//table.length = 20.0802;
	//table.origin = point;
	//table.price = 1438.5;

	//Container c;
	//c.width = 48.5;
	//c.length = 72.5;
	//if (c.doesFit(table)) {
	//	cout << "true" << endl;
	//}
	//else {
	//	cout << "false" << endl;
	//}

/*
0.0000,55.9085
29.9228,20.0802*/

	pause;
	return 0;
}



