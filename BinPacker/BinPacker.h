#pragma once
#include"NCLDS.h"
#include"Table.h"
#include"Container.h"

class BinPacker {
public:
	BinPacker();
	~BinPacker();
	static bool getInput();
	static Container cTemplate;
	static bool start();
};

