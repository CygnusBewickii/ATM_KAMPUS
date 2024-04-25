**Cash machine** 

Implement interface to ATM cash. The device received commands from standard input and sends replies to standard output. 

Accepted commands: **Add notes:** 

+ **<currency> <value> <number>** 

<currency> is 3 uppercase letters, any combination is valid 

<value> is the value of notes. Valid values are 10n and 5\*10n,  0<=n<=3 (although some currencies may have larger value notes and some odd values like 2,3,20,25, we do not allow them for simplification). 

<number> is any positive integer 

Semantics: put notes into cash 

Reply: OK if successful, ERROR if validation fails 

Example: 

+ USD 100 30 

***OK*** 

**Get cash:** 

- **<currency> <amount>** 

<currency> as described above, <amount> any positive integer 

Semantics: get the sum from the cash if possible. 

Reply: one line per each note value, formatted as <value> <number of notes>, followed with a line OK 

ERROR if the amount is unavailable (cash remains unchanged). 

Example: 

- USD 2000 

***100 20*** 

***OK*** 

**Print cash:** 

**?** 

Reply: one line for each currency/value pair <currency> <value> <number> 

followed by the line OK 

Lines are ordered by first currency, then by value. Semantics: what is currently in the cash 

Example: see in sample session. 

(Cash machine output is shown in ***green* *italics***). 

**Sample Session** 

**?** 

*OK* 

+ **USD 100 30** *OK* 
+ **RUR 250 10** *ERROR* 
+ **CHF 100 5** *OK* 
+ **USD 10 50** *OK* 

  **?** 

  *CHF 100 5 USD 10 50 USD 100 30 OK* 

- **USD 120** *100 1* 

  *10 2* 

  *OK* 

- **RUR 500** *ERROR* 
- **CHF 250** *ERROR* 

  **?** 

  *CHF 100 5 USD 10 48 USD 100 29 OK* 

+ **eur 100 5** *ERROR* 
- **CHF 400** *100 4* 

  *OK* 

+ **CHF 10 50** *OK* 

  **?** 

  *CHF 10 50 CHF 100 1 USD 10 48 USD 100 29 OK* 

  **Обязательно:** 

- написать программу на Java (любой JDK) – stans-alone приложение с консольным вводом-выводом; 
- программа должна быть написана с использованием ООП с тем, чтобы, потенциально, при желании, можно было относительно просто добавлять новые команды, изменять стратегию выдачи купюр, выводить данные не на консоль, а, например, в файл, читать данные не из консоли, а из любого другого входного устройства и т.д. 

  **Желательно:** 

- написать набор JUnit тестов для тестирования public методов бизнес-объектов; 
