import 'package:flutter/material.dart';

import 'package:we_chat_demo/pages/chat_page.dart';
import 'package:we_chat_demo/pages/discover_page.dart';
import 'package:we_chat_demo/pages/friends_page.dart';
import 'package:we_chat_demo/pages/mine_page.dart';


class RootPage extends StatefulWidget {
  @override
  _RootPageState createState() => _RootPageState();
}

class _RootPageState extends State<RootPage> {
  List <Widget> pages = [ChatPage(),FriendsPage(),DiscoverPage(),MinePage()];
  int _currentIndex = 2;
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Scaffold(
        bottomNavigationBar: BottomNavigationBar(
          onTap: (int index){
            _currentIndex = index;
            setState(() {});
          },
          selectedFontSize: 12.0,
          type: BottomNavigationBarType.fixed,
          fixedColor: Colors.green,
          currentIndex: _currentIndex,
          items: <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              icon:Image(height: 20,width: 20,image: AssetImage('images/tabbar_chat.png')),
              activeIcon: Image(height: 20,width: 20,image: AssetImage('images/tabbar_chat_hl.png')),
              title: Text('微信'),
            ),
            BottomNavigationBarItem(
              icon:Image(height: 20,width: 20,image: AssetImage('images/tabbar_friends.png')),
              activeIcon: Image(height: 20,width: 20,image: AssetImage('images/tabbar_friends_hl.png')),
              title: Text('通讯录'),
            ),
            BottomNavigationBarItem(
              icon:Image(height: 20,width: 20,image: AssetImage('images/tabbar_discover.png')),
              activeIcon: Image(height: 20,width: 20,image: AssetImage('images/tabbar_discover_hl.png')),
              title: Text('发现'),
            ),
            BottomNavigationBarItem(
              icon:Image(height: 20,width: 20,image: AssetImage('images/tabbar_mine.png')),
              activeIcon: Image(height: 20,width: 20,image: AssetImage('images/tabbar_mine_hl.png')),
              title: Text('我'),
            ),
          ],
        ),
        body:pages[_currentIndex],//微信
      ),
    );
  }
}
