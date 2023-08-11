import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';

class HomePage extends StatelessWidget {
  static const platform =
      const MethodChannel('com.zensar.myFlutterApp/surveyMonkey');
  static String sessionSurveyMonkeyHash = '7CNPBZP';

  Future _loadSurveyMonkey() async {
    try {
      await platform
          .invokeMethod('surveyMonkey', sessionSurveyMonkeyHash)
          .then((result) {
        Fluttertoast.showToast(
            msg: result,
            toastLength: Toast.LENGTH_SHORT,
            gravity: ToastGravity.CENTER,
            timeInSecForIosWeb: 1,
            backgroundColor: Colors.red,
            textColor: Colors.white,
            fontSize: 16.0);
      });
    } on PlatformException catch (e) {
      print(e.message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(""),
      ),
      body: Center(
        child: ElevatedButton(
          onPressed: _loadSurveyMonkey,
          child: Text("Load SurveyMonkey"),
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
