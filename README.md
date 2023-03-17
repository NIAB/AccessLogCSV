# AccessLogCsv
Recursively walks a directory and logs the access times of all files to a CSV

## Requirements
- Java 11+

## Usage
`java -jar AccessLogCsv.jar <directory> [output file]`

### Arguments
- `<directory>`
    - First arg
    - The directory to recursively walk
- `[output file]`
    - Second arg (optional)
    - The file to write the CSV to. If not specified, the CSV will be written to `output.csv` in the directory specified in the first arg.

## Example
`java -jar AccessLogCsv.jar "C:\Users\oscar.nardone\Documents\AccessLogCsv\build"`

```csv
Access time,Full path including filename
2023-03-17 11:43:52,C:\Users\oscar.nardone\Documents\AccessLogCsv\build
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\classes
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\classes\java
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\classes\java\main
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\classes\java\main\com
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\classes\java\main\com\niab
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\classes\java\main\com\niab\accesslogcsv
2023-03-17 11:39:13,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\classes\java\main\com\niab\accesslogcsv\App.class
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\distributions
2023-03-17 11:39:13,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\distributions\AccessLogCsv.tar
2023-03-17 11:39:13,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\distributions\AccessLogCsv.zip
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\generated
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\generated\sources
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\generated\sources\annotationProcessor
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\generated\sources\annotationProcessor\java
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\generated\sources\annotationProcessor\java\main
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\generated\sources\headers
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\generated\sources\headers\java
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\generated\sources\headers\java\main
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\libs
2023-03-17 11:39:23,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\libs\AccessLogCsv.jar
2023-03-17 11:43:52,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\output.csv
2023-03-17 11:39:52,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\output.csv.bak
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache\332a70c3pbfewdugwblcqz2r5
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache\332a70c3pbfewdugwblcqz2r5\5timaqj4qgmwex35qbauv57a8
2023-03-17 11:36:29,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache\332a70c3pbfewdugwblcqz2r5\5timaqj4qgmwex35qbauv57a8\configuration-cache-report.html
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache\332a70c3pbfewdugwblcqz2r5\ex72m4khyiszmdyaf2eth70r
2023-03-17 11:35:04,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache\332a70c3pbfewdugwblcqz2r5\ex72m4khyiszmdyaf2eth70r\configuration-cache-report.html
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache\d649dkdexfbmzuldnk3gpiy87
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache\d649dkdexfbmzuldnk3gpiy87\6bm3sofvmva9agwggr7padqxq
2023-03-17 11:30:25,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\reports\configuration-cache\d649dkdexfbmzuldnk3gpiy87\6bm3sofvmva9agwggr7padqxq\configuration-cache-report.html
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\scripts
2023-03-17 11:39:13,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\scripts\AccessLogCsv
2023-03-17 11:39:13,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\scripts\AccessLogCsv.bat
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction\annotation-output
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction\compile-output
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction\compile-output\com
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction\compile-output\com\niab
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction\compile-output\com\niab\accesslogcsv
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction\header-output
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction\stash-dir
2023-03-17 11:37:51,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\compileTransaction\stash-dir\App.class.uniqueId0
2023-03-17 11:39:13,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\compileJava\previous-compilation-data.bin
2023-03-17 11:43:53,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\jar
2023-03-17 11:39:13,C:\Users\oscar.nardone\Documents\AccessLogCsv\build\tmp\jar\MANIFEST.MF
```
