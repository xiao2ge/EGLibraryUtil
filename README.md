# EGLibraryUtil
工具类
[![](https://jitpack.io/v/xiao2ge/EGLibraryUtil.svg)](https://jitpack.io/#xiao2ge/EGLibraryUtil)

#### 初始化
```
EGUtilManager.init(application, fileProvider, debug);
```
#### 打印日志 EGLogUtils
```
void i(String tag, String msg)；
void e(String tag, String msg);
```

#### Toast EGToastUtils
```
void showLong(String info)；
void showShort(String info)；
```

#### UI EGUIUtils
```
int px2dp(float pxValue);
int dp2px(float dpValue);
```

#### 字符串 EGStringUtils
```
String getTextViewString(TextView tv)； // 获取文本控件的值

String formatTimeNow()； // 格式化当前时间为 HH:mm:ss

String formatDateNow19(); // 格式化当前时间为 yyyy-MM-dd HH:mm:ss

String formatDateNow10(); // 格式化当前时间为 yyyy-MM-dd

String formatDate19(Date date); // 格式化date时间为 yyyy-MM-dd HH:mm:ss

String formatDateNow(String toPattern); // 格式化当前时间为toPattern格式

String formatDate(String date, String fromPattern, String toPattern); // 将fromPattern格式日期从新格式化为 toPattern

List<String> getStrList(String inputString, int length); // 把原始字符串分割成指定长度的字符串列表
```
