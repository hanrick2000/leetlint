# POJ 50题

标签（空格分隔）： POJ ACM

---

## POJ 50题
* POJ推荐50题 —— 参加06年BUPT ACM暑假集训前要求完成  
* POJ == 北京大学ACM在线评测系统 [http://acm.pku.edu.cn/JudgeOnline](http://acm.pku.edu.cn/JudgeOnline)  

1. 标记 难 和 稍难 的题目大家可以看看，思考一下，不做要求，当然有能力的同学可以直接切掉。  

2. 标记为 A and B 的题目是比较相似的题目，建议大家两个一起做，可以对比总结，且二者算作一个题目。  

3. 列表中大约有70个题目。大家选做其中的50道，且每类题目有最低数量限制。  

4. 这里不少题目在 BUPT ACM FTP 上面都有代码，请大家合理利用资源。  

5. 50个题目要求每个题目都要写总结，养成良好的习惯。  

6. 这50道题的规定是我们的建议，如果大家有自己的想法请与我们 Email 联系。  

7. 建议使用 C++ 的同学在 POJ 上用 G++ 提交。  

8. 形成自己编写代码的风格，至少看上去美观，思路清晰(好的代码可以很清楚反映出解题思路)。  

9. 这个列表的目的在于让大家对各个方面的算法有个了解，也许要求有些苛刻，教条，请大家谅解，这些是我们这些年的经验总结，所以也请大家尊重我们的劳动成果。  

10. 提交要求：一个总文件夹名为 bupt0xx (即你的比赛帐号), 这个文件夹内有各个题目类别的子目录(文件夹)，将相应的解题报告放入对应类别的文件夹。在本学期期末，小学期开始前，将该文件夹的压缩包发至 buptacm@gmail.com 。 对于每个题目只要求一个 POJxxxx.cpp 或 POJxxxx.java (xxxx表示POJ该题题号) 的文件，注意不要加入整个 project 。  

11. 如果有同学很早做完了要求的题目，请尽快和我们联系，我们将指导下一步的训练。  

下面是一个解题报告的范例：  
```
例如：POJ1000.cpp  

//考查点：会不会编程序。  
//思路：此题要求输入两个数， 输出两个数的和，我用 scanf 和 printf。  
//提交情况：Wrong Answer 1次，忘了写 printf()。  
?????????? Compile Error 2次，选错了语言，由于C++ 和 G++ 在 iostream.h 的不用引用方法；少一个大括号。  
Accepted 1次。  

//收获：学到了 scanf, printf 的基本用法，熟悉了 OJ 的系统环境。  
//经验： 写好代码后本地编译 而且需要静态 观察，杜绝编译错误。  

// AC Code  
#include <stdio.h>  
int main() {  
int a,b;  
scanf("%d%d",&a,&b);  
printf("%d\n",a+b);  
return 0;  
}  
```

第一类 动态规划 (至少6题，2479 and 2593必做)  

[2479](http://acm.pku.edu.cn/JudgeOnline/problem?id=2479) and [2593](http://acm.pku.edu.cn/JudgeOnline/problem?id=2593)  

[1015](http://acm.pku.edu.cn/JudgeOnline/problem?id=1015)  

[1042](http://acm.pku.edu.cn/JudgeOnline/problem?id=1042) (也可贪心)  

[1141](http://acm.pku.edu.cn/JudgeOnline/problem?id=1141)  

[1050](http://acm.pku.edu.cn/JudgeOnline/problem?id=1050)  

[1080](http://acm.pku.edu.cn/JudgeOnline/problem?id=1080)  

[1221](http://acm.pku.edu.cn/JudgeOnline/problem?id=1221)  

[1260](http://acm.pku.edu.cn/JudgeOnline/problem?id=1260)  

[2411](http://acm.pku.edu.cn/JudgeOnline/problem?id=2411) (稍难)  

[1276](http://acm.pku.edu.cn/JudgeOnline/problem?id=1276)  

第二类 搜索 (至少4题)  

[1011](http://acm.pku.edu.cn/JudgeOnline/problem?id=1011)  

[1033](http://acm.pku.edu.cn/JudgeOnline/problem?id=1033)  

[1129](http://acm.pku.edu.cn/JudgeOnline/problem?id=1129)  

[2049](http://acm.pku.edu.cn/JudgeOnline/problem?id=2049)  

[2056](http://acm.pku.edu.cn/JudgeOnline/problem?id=2056)  

[2488](http://acm.pku.edu.cn/JudgeOnline/problem?id=2488)  

[2492](http://acm.pku.edu.cn/JudgeOnline/problem?id=2492) (稍难，也可并查集)  

第三类 贪心 (至少2题)  

[1065](http://acm.pku.edu.cn/JudgeOnline/problem?id=1065)  

[2054](http://acm.pku.edu.cn/JudgeOnline/problem?id=2054) (难)  

[1521](http://acm.pku.edu.cn/JudgeOnline/problem?id=1521)  

[2709](http://acm.pku.edu.cn/JudgeOnline/problem?id=2709)  

第四类 最短路 (至少3题)  

[1062](http://acm.pku.edu.cn/JudgeOnline/problem?id=1062)  

[1125](http://acm.pku.edu.cn/JudgeOnline/problem?id=1125)  

[1797](http://acm.pku.edu.cn/JudgeOnline/problem?id=1797)  

[2253](http://acm.pku.edu.cn/JudgeOnline/problem?id=2253)  

[2679](http://acm.pku.edu.cn/JudgeOnline/problem?id=2679) Bellman-Ford (难)  

第五类 最小生成树 (至少2题, 而且 Prim 和 Kruskal 至少各用一次)  

[1251](http://acm.pku.edu.cn/JudgeOnline/problem?id=1251)  

[1258](http://acm.pku.edu.cn/JudgeOnline/problem?id=1258)  

[1789](http://acm.pku.edu.cn/JudgeOnline/problem?id=1789)  

[2485](http://acm.pku.edu.cn/JudgeOnline/problem?id=2485)  

第六类 最大流 (至少2题)  

[1087](http://acm.pku.edu.cn/JudgeOnline/problem?id=1087)  

[1459](http://acm.pku.edu.cn/JudgeOnline/problem?id=1459)  

[1149](http://acm.pku.edu.cn/JudgeOnline/problem?id=1149)  

[2516](http://acm.pku.edu.cn/JudgeOnline/problem?id=2516) (最小费用最大流) (难)  

第七类 二分图 (至少3题)  

[1325](http://acm.pku.edu.cn/JudgeOnline/problem?id=1325)  

[1469](http://acm.pku.edu.cn/JudgeOnline/problem?id=1469)  

[2195](http://acm.pku.edu.cn/JudgeOnline/problem?id=2195) (KM 算法或最小费用最大流) (难)  

[2446](http://acm.pku.edu.cn/JudgeOnline/problem?id=2446)  

[1422](http://acm.pku.edu.cn/JudgeOnline/problem?id=1422) and [2594](http://acm.pku.edu.cn/JudgeOnline/problem?id=2594)  

第八类 并查集 (至少2题)  

[1861](http://acm.pku.edu.cn/JudgeOnline/problem?id=1861)  

[1182](http://acm.pku.edu.cn/JudgeOnline/problem?id=1182) (难)  

[1308](http://acm.pku.edu.cn/JudgeOnline/problem?id=1308)  

[2524](http://acm.pku.edu.cn/JudgeOnline/problem?id=2524)  

第九类 快速查找 (B-Search, Hash and so on) (至少3题)  

[2503](http://acm.pku.edu.cn/JudgeOnline/problem?id=2503)  

[2513](http://acm.pku.edu.cn/JudgeOnline/problem?id=2513) (+Euler回路的判定)  

[1035](http://acm.pku.edu.cn/JudgeOnline/problem?id=1035)  

[1200](http://acm.pku.edu.cn/JudgeOnline/problem?id=1200)  

[2002](http://acm.pku.edu.cn/JudgeOnline/problem?id=2002)  

第十类 数论 (至少2题)  

[1061](http://acm.pku.edu.cn/JudgeOnline/problem?id=1061)  

[1142](http://acm.pku.edu.cn/JudgeOnline/problem?id=1142)  

[2262](http://acm.pku.edu.cn/JudgeOnline/problem?id=2262)  

[2407](http://acm.pku.edu.cn/JudgeOnline/problem?id=2407)  

[1811](http://acm.pku.edu.cn/JudgeOnline/problem?id=1811)(难)  

[2447](http://acm.pku.edu.cn/JudgeOnline/problem?id=2447) (难)  

第十一类 线段树 (无最少题数要求)  

[2352](http://acm.pku.edu.cn/JudgeOnline/problem?id=2352) (可用简单方法)  

[2528](http://acm.pku.edu.cn/JudgeOnline/problem?id=2528)  

第十二类 计算几何 (至少2题，1113凸包算法必做)  

[1113](http://acm.pku.edu.cn/JudgeOnline/problem?id=1113)  

[1292](http://acm.pku.edu.cn/JudgeOnline/problem?id=1292)  

[2148](http://acm.pku.edu.cn/JudgeOnline/problem?id=2148) (难)  

[2653](http://acm.pku.edu.cn/JudgeOnline/problem?id=2653)  

[1584](http://acm.pku.edu.cn/JudgeOnline/problem?id=1584)  

第十三类 高精度 (至少3题，1001必做)  

[1001](http://acm.pku.edu.cn/JudgeOnline/problem?id=1001)  

[1047](http://acm.pku.edu.cn/JudgeOnline/problem?id=1047)  

[1131](http://acm.pku.edu.cn/JudgeOnline/problem?id=1131)  

[1503](http://acm.pku.edu.cn/JudgeOnline/problem?id=1503)  

[1504](http://acm.pku.edu.cn/JudgeOnline/problem?id=1504)  

[1060](http://acm.pku.edu.cn/JudgeOnline/problem?id=1060) and [1996](http://acm.pku.edu.cn/JudgeOnline/problem?id=1996) (多项式)  

SCU1002, 1003, 1004 ([http://acm.scu.edu.cn/soj](http://acm.scu.edu.cn/soj))  

第十四类 模拟 (至少5题)  

[1029](http://acm.pku.edu.cn/JudgeOnline/problem?id=1029) and [1013](http://acm.pku.edu.cn/JudgeOnline/problem?id=1013)  

[1083](http://acm.pku.edu.cn/JudgeOnline/problem?id=1083) and [2028](http://acm.pku.edu.cn/JudgeOnline/problem?id=2028)  

[2234](http://acm.pku.edu.cn/JudgeOnline/problem?id=2234) and [1067](http://acm.pku.edu.cn/JudgeOnline/problem?id=1067)  

[1012](http://acm.pku.edu.cn/JudgeOnline/problem?id=1012)  

[1026](http://acm.pku.edu.cn/JudgeOnline/problem?id=1026)  

[1068](http://acm.pku.edu.cn/JudgeOnline/problem?id=1068)  

[1120](http://acm.pku.edu.cn/JudgeOnline/problem?id=1120)  

[2271](http://acm.pku.edu.cn/JudgeOnline/problem?id=2271)  

[2632](http://acm.pku.edu.cn/JudgeOnline/problem?id=2632)  

第十五类 数学 (至少4题)  

[2249](http://acm.pku.edu.cn/JudgeOnline/problem?id=2249)  

[1023](http://acm.pku.edu.cn/JudgeOnline/problem?id=1023)  

[2506](http://acm.pku.edu.cn/JudgeOnline/problem?id=2506)  

[1079](http://acm.pku.edu.cn/JudgeOnline/problem?id=1079)  

[1019](http://acm.pku.edu.cn/JudgeOnline/problem?id=1019) and [1095](http://acm.pku.edu.cn/JudgeOnline/problem?id=1095)  

[1905](http://acm.pku.edu.cn/JudgeOnline/problem?id=1905) and [1064](http://acm.pku.edu.cn/JudgeOnline/problem?id=1064) (二分)  

备注：  

你可以到北邮人论坛“算法与程序设计竞赛”版（ACM_ICPC）讨论这些题目，但请不要贴自己通过的代码，否则后果自负。同时你也可以在  

BUPT ACM QQ群里寻求帮助。另外，BUPT ACM FTP中有很多参考资料可供使用。如果对《POJ推荐50题》有任何疑问，请通过Email或北邮人我们  

的版面提出。  

请各位同学独立完成题目，我们会对你最后提交的代码进行严格的检查。  

集训队Email: buptacm@gmail.com  

BUPT ACM QQ群: 16023016  

北邮人论坛 算法与程序设计竞赛版  

http://forum.byr.edu.cn/wForum/board.php?name=ACM_ICPC  

BUPT ACM FTP  

Address: [ftp://www.cs.bupt.cn/acm](ftp://www.cs.bupt.cn/acm)  

ID: acmguest  

PASSWORD: acmftp  

(注意地址后面有/acm)  

--  




