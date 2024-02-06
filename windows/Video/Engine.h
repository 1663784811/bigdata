#pragma once
#include <windows.h>
#include <string>

class Engine
{
public:
	// 构造函数
	Engine(HINSTANCE hInstance, std::string window_class);
	// 析构函数
	~Engine();
	//  创建一个窗口
	bool Initialize(std::string window_title, int width, int heigth, int nCmdShow);
	
	bool RegisterWindowsClass();


	bool ProcessMessage();

	void Close();

	void Renderer();

private:
	static LRESULT CALLBACK HandleMessageSetup(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam);
	static LRESULT HandleMsgRedirect(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam);
	LRESULT WinProc(HWND hwnd, UINT uMsg, WPARAM wParam, LPARAM lParam);
	void OnStart();
	void OnClose();

private:
	HWND handle;
	HINSTANCE hInstance;

	std::string window_title;
	std::wstring window_title_wide;

	std::string window_class;
	std::wstring window_class_wide;

	int width;
	int height;
};

