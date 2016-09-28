#pragma once
#include"NCLDS.h"
#include"Table.h"
#include"Container.h"
#include<thread>
#include<mutex>

class BinPacker {
public:
	static mutex _mutex;
	static std::vector<thread> workers;
	BinPacker();
	~BinPacker();
	static bool getInput();
	static Container cTemplate;
//	static bool start();
	static bool start();
	static bool fWork(int i);
};

