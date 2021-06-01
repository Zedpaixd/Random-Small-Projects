#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <string>
#include <cstring>
#include <fstream>

using namespace std;



void addList(struct list* top, int line);
void addLine(int line, string word);
struct node* insertNode(node* node, int line, string word);



struct list

	{int linectr;
	struct list *following;};




struct node

	{struct node* left;
	struct node* right;
	struct list* textline;
	string word;};



struct node* root;



struct node* createNode(int line, string word)

	{struct node* nodeTemp = new node();
	struct list* listTemp = new list();

	nodeTemp->word = word;
	listTemp->linectr = line;

	nodeTemp->left = nullptr;
	nodeTemp->right = nullptr;
	listTemp->following = nullptr;

	nodeTemp->textline = listTemp;

	return nodeTemp;}



bool isNode(node* node, string word)

	{if (node == nullptr)
		{return false;}

	else if (node->word == word)
		{return true;}

	else if (node->word > word)
		{return isNode(node->left, word);}

	else
		{return isNode(node->right, word);}}



struct node* insertNode(node* node, int line, string word)

	{bool check;
	check = isNode(root, word);

	if (check == true)
		{addLine(line, word);
		return node;}

	else
		{struct node* temp = createNode(line, word);

		if (node == nullptr)
			{node = temp;
			return node;}

		if (node->word > word)
			{node->left = insertNode(node->left, line, word);}

		else
			{node->right = insertNode(node->right, line, word);}

		return node;
	}
}



void addLine(int line, string word)

	{struct node* temp = root;

	while (temp)
		{if (temp->word == word)
			{addList(temp->textline, line);
			break;}

		else if (temp->word > word)
			{temp = temp->left;}

		else
			{temp = temp->right;}}}



void ReadFile()
{

	char splittext[99999];
	int counter = 0;
	ifstream input("Input.txt");

	while (input.getline(splittext, 99999))
		{counter++;
		char* word;
		word = strtok(splittext, " .!?,-;:/\\");

		while (word != NULL)
			{string str(word);
			root = insertNode(root, counter, str);
			word = strtok(NULL, " .!?,-;:/\\");}}}



void addList(struct list* top, int line)

	{struct list* temp = new list();

	temp->linectr = line;
	temp->following = nullptr;

	struct list* list = top;

	while (list->following != nullptr)
		{list = list->following;}

	list->following = temp;}



void recursiveTravel(node* node)

	{if (node == nullptr)
		return;

	recursiveTravel(node->left);
	cout << "word: " << node->word << " | " << "appears on lines: ";

	struct list* temp = node->textline;

	while (temp)
		{cout << temp->linectr << " ";
		temp = temp->following;}

	cout << endl << "-----------------------------------------------------" << endl;
	recursiveTravel(node->right);}



int main()
	{while (1)

		{system("cls");

		cout << "If not done already, please input your text in the Input.txt file.\n";
		cout << "------------------------------------------------------------------\n";
		cout << "1. Proceed\n";
		cout << "2. Exit.\n";
		cout << "------------------------------------------------------------------\n";
		cout << "Input: ";

		int choice;

		cin >> choice;

		switch (choice)

			{case 1:
				{system("cls");
				ReadFile();
				recursiveTravel(root);
				return 0;}

			case 2:
				{return 0;}}}}


