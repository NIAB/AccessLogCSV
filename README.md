# AccessLogCSV
Recursively walks a directory and logs the access times of all folders to a CSV. The latest file access time in a folder is used for the logged folder's access time.

## Download
You can download the jar from the releases page: https://github.com/NIAB/AccessLogCSV/releases

## Usage
`java -jar AccessLogCsv.jar <directory> [output file]`

To get the version: `java -jar AccessLogCsv.jar version`

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
Access time,Full path
2023-03-21 11:47:13,C:\Users\oscar.nardone\Documents\GitHub\AccessLogCsv\build
2023-03-21 11:45:36,C:\Users\oscar.nardone\Documents\GitHub\AccessLogCsv\build\distributions
2023-03-21 11:47:13,C:\Users\oscar.nardone\Documents\GitHub\AccessLogCsv\build\libs
2023-03-21 11:45:36,C:\Users\oscar.nardone\Documents\GitHub\AccessLogCsv\build\scripts
2023-03-21 11:45:36,C:\Users\oscar.nardone\Documents\GitHub\AccessLogCsv\build\tmp\compileJava
2023-03-21 11:45:36,C:\Users\oscar.nardone\Documents\GitHub\AccessLogCsv\build\tmp\jar
2023-03-21 11:41:17,C:\Users\oscar.nardone\Documents\GitHub\AccessLogCsv\build\tmp\compileJava\compileTransaction\stash-dir
2023-03-21 11:45:36,C:\Users\oscar.nardone\Documents\GitHub\AccessLogCsv\build\classes\java\main\com\niab\accesslogcsv
```
