import 'package:flutter/material.dart';
import 'package:azy_app/modul/acupoint/find_acupoint_page.dart';
import 'package:azy_app/modul/home/home_page.dart';
import 'package:azy_app/modul/mine/my_page.dart';

class RootPage extends StatefulWidget {
  @override
  _RootPageState createState() => _RootPageState();
}

class _RootPageState extends State<RootPage> {
  int currentIndex = 0;
  List pages = [
    HomePage(),
    FindAcupointPage(),
    MyPage(),
  ];

  List<BottomNavigationBarItem> items = [
    createItem("首页", "assets/tabbar/main_tab_home@3x.png", "assets/tabbar/main_tab_home_selected@3x.png"),
    createItem("找穴赵方", "assets/tabbar/main_tab_recipe@3x.png", "assets/tabbar/main_tab_recipe_selected@3x.png"),
    createItem("我的", "assets/tabbar/main_tab_me@3x.png", "assets/tabbar/main_tab_me_selected@3x.png"),
  ];

  static BottomNavigationBarItem createItem(String title, String imgName, String imgSelName) {
    return BottomNavigationBarItem(
      icon: Image(image: AssetImage(imgName), width: 20, height: 20,),
      activeIcon: Image(image: AssetImage(imgSelName), width: 20, height: 20,),
      title: Text(title),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Scaffold(
        bottomNavigationBar: BottomNavigationBar(
          backgroundColor: Colors.white,
          type: BottomNavigationBarType.fixed,
          selectedFontSize: 12,
          unselectedFontSize: 12,
          currentIndex: currentIndex,
          iconSize: 20,
          fixedColor: Color(0xff7ed8d0),
          onTap: (int index) {
            setState(() {
              currentIndex = index;
            });
          },
          items: [
            BottomNavigationBarItem(
            icon: Image(image: AssetImage("assets/tabbar/main_tab_home@3x.png"), width: 20, height: 20,),
            activeIcon: Image(image: AssetImage("assets/tabbar/main_tab_home_selected@3x.png"), width: 20, height: 20,),
            
            title: Text("首页", style: TextStyle(fontSize: 12),),
    ),
            BottomNavigationBarItem(
            icon: Image(image: AssetImage("assets/tabbar/main_tab_home@3x.png"), width: 20, height: 20,),
            activeIcon: Image(image: AssetImage("assets/tabbar/main_tab_home_selected@3x.png"), width: 20, height: 20,),
            title: Text("首页"),)
          ],
        ),
        body: pages[currentIndex],
      ),
    );
  }
}
