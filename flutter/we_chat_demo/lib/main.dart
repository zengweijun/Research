import 'package:flutter/material.dart';

import 'package:we_chat_demo/pages/root_page.dart';

//void main() => runApp(MyApp());

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',//安卓里面
      theme: ThemeData(
        primarySwatch: Colors.blue,
        highlightColor: Color.fromRGBO(1, 0, 0, 0.0),
        splashColor:  Color.fromRGBO(1, 0, 0, 0.0),
      ),
      home: RootPage(),
    );
  }
}






