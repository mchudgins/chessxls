<!-- $Id$ -->
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
    xmlns:fn="http://java.sun.com/jsp/jstl/functions"
    xmlns="http://www.w3.org/1999/xhtml"
    version="2.0">
<jsp:directive.page contentType="text/html" isELIgnored="false" pageEncoding="UTF-8" />
	<jsp:output omit-xml-declaration="true" />
	<jsp:output doctype-root-element="html"
		doctype-system="about:legacy-compat" />    
 	 
<html>
	<head>
		<title>Chess XLS ${pageTitle}</title>
		<jsp:directive.include file="head-boilerplate.xml" />
		<!-- this one little ol' bit o' css is needed in the jsp file because the background image logo url relies on the current app.imagePath -->
 	</head>
	<body>
		<h1>Tim's <em>Official</em> Chess Spreadsheet</h1>
		
		<div id="statsTable" >
		<h2>Player Standings</h2>
        <table cellpadding="0" cellspacing="0" border="0" id="table" class="tinytablez">
            <thead>
                <tr>
                    <th class="nosort"><h3>ID</h3></th>
                    <th class="fubar"><h3>Name</h3></th>
                    <th><h3>Phone</h3></th>
                    <th><h3>Email</h3></th>
                    <th><h3>Birthdate</h3></th>
                    <th><h3>Last Access</h3></th>
                    <th><h3>Rating</h3></th>
                    <th><h3>Done</h3></th>
                    <th><h3>Salary</h3></th>
                    <th><h3>Score</h3></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="number_cell">1</td>
                    <td><a href="fubar/yuksnort">Ezekiel Hart</a></td>
                    <td>(627) 536-4760</td>
                    <td><a href="mailto:#">tortor@est.ca</a></td>
                    <td>12/02/1962</td>
                    <td>March 26, 2009</td>
                    <td>-7</td>
                    <td>7%</td>
                    <td>$73,229</td>
                    <td>6.9</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Jaquelyn Pace</td>
                    <td>(921) 943-5780</td>
                    <td><a href="mailto:#">in@elementum.org</a></td>
                    <td>06/03/1957</td>
                    <td>October 20, 2006</td>
                    <td>-7</td>
                    <td>33%</td>
                    <td>$130,752</td>
                    <td>4.9</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Lois Pickett</td>
                    <td>(835) 361-5993</td>
                    <td><a href="mailto:#">arcu.ac@disse.ca</a></td>
                    <td>10/15/1983</td>
                    <td>June 01, 1999</td>
                    <td>4</td>
                    <td>44%</td>
                    <td>$48,684</td>
                    <td>5.5</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Keane Raymond</td>
                    <td>(605) 803-1561</td>
                    <td><a href="mailto:#">at.risus.Nunc@ipsum.com</a></td>
                    <td>07/30/1982</td>
                    <td>July 24, 1996</td>
                    <td>5</td>
                    <td>20%</td>
                    <td>$7,023</td>
                    <td>9.5</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>Porter Thomas</td>
                    <td>(666) 569-9894</td>
                    <td><a href="mailto:#">non@Proin.ca</a></td>
                    <td>09/27/1986</td>
                    <td>December 05, 2007</td>
                    <td>1</td>
                    <td>66%</td>
                    <td>$69,875</td>
                    <td>0.9</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>Imani Murphy</td>
                    <td>(771) 294-6690</td>
                    <td><a href="mailto:#">Aenean.sed@elit.ca</a></td>
                    <td>10/23/1970</td>
                    <td>December 08, 1996</td>
                    <td>-1</td>
                    <td>30%</td>
                    <td>$113,763</td>
                    <td>4.9</td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>Zachery Guthrie</td>
                    <td>(851) 784-4129</td>
                    <td><a href="mailto:#">nunc.nulla@vel.com</a></td>
                    <td>12/22/1972</td>
                    <td>September 20, 2002</td>
                    <td>-5</td>
                    <td>24%</td>
                    <td>$130,248</td>
                    <td>6.9</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>Harper Bowen</td>
                    <td>(810) 652-6704</td>
                    <td><a href="mailto:#">dis@duinec.ca</a></td>
                    <td>10/26/1973</td>
                    <td>May 29, 1996</td>
                    <td>5</td>
                    <td>49%</td>
                    <td>$73,197</td>
                    <td>4.5</td>
                </tr>
                <tr>
                    <td>9</td>
                    <td>Caldwell Larson</td>
                    <td>(850) 562-3177</td>
                    <td><a href="mailto:#">elit@dolor.com</a></td>
                    <td>07/20/1985</td>
                    <td>June 22, 2004</td>
                    <td>-3</td>
                    <td>81%</td>
                    <td>$63,736</td>
                    <td>7.5</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>Baker Osborn</td>
                    <td>(378) 371-0559</td>
                    <td><a href="mailto:#">turpis.Nulla@ac.edu</a></td>
                    <td>03/29/1970</td>
                    <td>July 23, 2005</td>
                    <td>-7</td>
                    <td>61%</td>
                    <td>$2,868</td>
                    <td>0.1</td>
                </tr>
                <tr>
                    <td>11</td>
                    <td>Yael Owens</td>
                    <td>(465) 520-1801</td>
                    <td><a href="mailto:#">nunc.ac.mattis@enim.com</a></td>
                    <td>08/10/1963</td>
                    <td>April 09, 1997</td>
                    <td>10</td>
                    <td>85%</td>
                    <td>$126,469</td>
                    <td>8.9</td>
                </tr>
                <tr>
                    <td>12</td>
                    <td>Fletcher Briggs</td>
                    <td>(992) 962-9419</td>
                    <td><a href="mailto:#">amet.ante@lentesque.edu</a></td>
                    <td>08/12/1971</td>
                    <td>December 12, 2006</td>
                    <td>7</td>
                    <td>23%</td>
                    <td>$142,448</td>
                    <td>8.9</td>
                </tr>
                <tr>
                    <td>13</td>
                    <td>Maggy Murphy</td>
                    <td>(585) 210-0390</td>
                    <td><a href="mailto:#">eu@Integer.com</a></td>
                    <td>07/11/1968</td>
                    <td>April 02, 2007</td>
                    <td>9</td>
                    <td>31%</td>
                    <td>$40,267</td>
                    <td>6.9</td>
                </tr>
                <tr>
                    <td>14</td>
                    <td>Maggie Blake</td>
                    <td>(489) 101-5447</td>
                    <td><a href="mailto:#">rutrum.non.hendrerit@iaculis.org</a></td>
                    <td>04/11/1970</td>
                    <td>May 24, 2008</td>
                    <td>-2</td>
                    <td>32%</td>
                    <td>$99,686</td>
                    <td>7.9</td>
                </tr>
                <tr>
                    <td>15</td>
                    <td>Ginger Bell</td>
                    <td>(934) 692-7294</td>
                    <td><a href="mailto:#">erat.in.conetuer@pedenout.org</a></td>
                    <td>06/10/1957</td>
                    <td>April 13, 2003</td>
                    <td>-10</td>
                    <td>74%</td>
                    <td>$112,997</td>
                    <td>4.5</td>
                </tr>
                <tr>
                    <td>16</td>
                    <td>Iliana Ballard</td>
                    <td>(806) 835-7035</td>
                    <td><a href="mailto:#">vel.sapien@mi.ca</a></td>
                    <td>02/09/1989</td>
                    <td>March 27, 1996</td>
                    <td>-6</td>
                    <td>78%</td>
                    <td>$5,282</td>
                    <td>5.5</td>
                </tr>
                <tr>
                    <td>17</td>
                    <td>Alisa Monroe</td>
                    <td>(859) 974-4442</td>
                    <td><a href="mailto:#">adipiscing.ligula@aretraNam.edu</a></td>
                    <td>02/14/1990</td>
                    <td>April 30, 2003</td>
                    <td>6</td>
                    <td>95%</td>
                    <td>$103,999</td>
                    <td>5.9</td>
                </tr>
                <tr>
                    <td>18</td>
                    <td>Kenyon Luna</td>
                    <td>(673) 147-0443</td>
                    <td><a href="mailto:#">Cras@Vestibulumant.edu</a></td>
                    <td>04/14/1981</td>
                    <td>April 17, 2009</td>
                    <td>9</td>
                    <td>14%</td>
                    <td>$37,014</td>
                    <td>7.9</td>
                </tr>
                <tr>
                    <td>19</td>
                    <td>Nola Kerr</td>
                    <td>(340) 430-0424</td>
                    <td><a href="mailto:#">tincidunt@vurmagna.com</a></td>
                    <td>11/06/1959</td>
                    <td>August 14, 2000</td>
                    <td>10</td>
                    <td>2%</td>
                    <td>$104,149</td>
                    <td>0.5</td>
                </tr>
                <tr>
                    <td>20</td>
                    <td>Gail Cash</td>
                    <td>(291) 455-8520</td>
                    <td><a href="mailto:#">massa.rutrum@Nullamlob.ca</a></td>
                    <td>09/09/1970</td>
                    <td>April 27, 2002</td>
                    <td>-1</td>
                    <td>84%</td>
                    <td>$31,090</td>
                    <td>1.5</td>
                </tr>
                <tr>
                    <td>21</td>
                    <td>Kalia Ayala</td>
                    <td>(142) 520-1128</td>
                    <td><a href="mailto:#">Etiam.laoreet@velit.org</a></td>
                    <td>04/25/1971</td>
                    <td>March 30, 2001</td>
                    <td>5</td>
                    <td>30%</td>
                    <td>$44,641</td>
                    <td>5.9</td>
                </tr>
                <tr>
                    <td>22</td>
                    <td>Violet Ballard</td>
                    <td>(126) 374-6078</td>
                    <td><a href="mailto:#">Integer.in.magna@ntumcollis.edu</a></td>
                    <td>01/23/1984</td>
                    <td>June 08, 2006</td>
                    <td>-10</td>
                    <td>85%</td>
                    <td>$46,450</td>
                    <td>7.9</td>
                </tr>
                <tr>
                    <td>23</td>
                    <td>Kevin Carrillo</td>
                    <td>(687) 483-9669</td>
                    <td><a href="mailto:#">in@adipiscing.edu</a></td>
                    <td>03/17/1977</td>
                    <td>October 01, 2005</td>
                    <td>-3</td>
                    <td>5%</td>
                    <td>$51,754</td>
                    <td>1.5</td>
                </tr>
                <tr>
                    <td>24</td>
                    <td>Alexandra Nixon</td>
                    <td>(422) 644-3488</td>
                    <td><a href="mailto:#">nec.luctus@ornarefacilisis.com</a></td>
                    <td>12/01/1981</td>
                    <td>February 25, 2001</td>
                    <td>-1</td>
                    <td>41%</td>
                    <td>$46,672</td>
                    <td>7.5</td>
                </tr>
                <tr>
                    <td>25</td>
                    <td>Charissa Manning</td>
                    <td>(438) 395-9392</td>
                    <td><a href="mailto:#">nibh.vulputate@necelendnon.org</a></td>
                    <td>07/01/1980</td>
                    <td>April 02, 2005</td>
                    <td>-8</td>
                    <td>11%</td>
                    <td>$32,193</td>
                    <td>3.5</td>
                </tr>
                <tr>
                    <td>26</td>
                    <td>Noah Smith</td>
                    <td>(963) 652-2643</td>
                    <td><a href="mailto:#">Sed.neque@Duis.org</a></td>
                    <td>01/19/1986</td>
                    <td>April 03, 2005</td>
                    <td>-5</td>
                    <td>86%</td>
                    <td>$96,995</td>
                    <td>3.5</td>
                </tr>
                <tr>
                    <td>27</td>
                    <td>Patience Battle</td>
                    <td>(294) 644-5306</td>
                    <td><a href="mailto:#">tempus.mauris@elempurus.com</a></td>
                    <td>09/16/1988</td>
                    <td>October 19, 2003</td>
                    <td>7</td>
                    <td>62%</td>
                    <td>$47,510</td>
                    <td>4.5</td>
                </tr>
                <tr>
                    <td>28</td>
                    <td>Kathleen Hudson</td>
                    <td>(190) 189-1420</td>
                    <td><a href="mailto:#">orci.quis@auctor.com</a></td> 
                    <td>08/03/1963</td>
                    <td>January 03, 2004</td>
                    <td>8</td>
                    <td>27%</td>
                    <td>$36,286</td>
                    <td>6.9</td>
                </tr>
                <tr>
                    <td>29</td>
                    <td>Marsden Bowman</td>
                    <td>(163) 780-6121</td>
                    <td><a href="mailto:#">mauris.a@Sedcongueelit.edu</a></td>
                    <td>03/08/1974</td>
                    <td>June 29, 2005</td>
                    <td>10</td>
                    <td>46%</td>
                    <td>$124,913</td>
                    <td>1.5</td>
                </tr>
                <tr>
                    <td>30</td>
                    <td>Dorian Hodge</td>
                    <td>(304) 536-8850</td>
                    <td><a href="mailto:#">pellentesque@laoreet.org</a></td>
                    <td>08/16/1978</td>
                    <td>February 21, 2007</td>
                    <td>6</td>
                    <td>6%</td>
                    <td>$28,057</td>
                    <td>0.1</td>
                </tr>
                <tr>
                    <td>31</td>
                    <td>Levi Britt</td>
                    <td>(272) 171-5731</td>
                    <td><a href="mailto:#">felis@Donecfeugiat.ca</a></td> 
                    <td>12/10/1988</td>
                    <td>August 11, 2008</td>
                    <td>7</td>
                    <td>6%</td>
                    <td>$100,959</td>
                    <td>1.5</td>
                </tr>
                <tr>
                    <td>32</td>
                    <td>Rana Blake</td>
                    <td>(608) 893-4909</td>
                    <td><a href="mailto:#">malesuada.fames@dui.edu</a></td>
                    <td>07/23/1959</td>
                    <td>February 13, 1997</td>
                    <td>-4</td>
                    <td>26%</td>
                    <td>$87,972</td>
                    <td>4.9</td>
                </tr>
                <tr>
                    <td>33</td>
                    <td>Helen Mccullough</td>
                    <td>(937) 368-5946</td>
                    <td><a href="mailto:#">sociis.natoque@myipsum.org</a></td>
                    <td>09/13/1980</td>
                    <td>May 17, 2001</td>
                    <td>8</td>
                    <td>18%</td>
                    <td>$51,217</td>
                    <td>9.9</td>
                </tr>
                <tr>
                    <td>34</td>
                    <td>Gil Ferguson</td>
                    <td>(832) 581-6953</td>
                    <td><a href="mailto:#">libero@Infaucibus.com</a></td> 
                    <td>04/19/1980</td>
                    <td>March 22, 1996</td>
                    <td>-6</td>
                    <td>83%</td>
                    <td>$120,374</td>
                    <td>6.9</td>
                </tr>
                <tr>
                    <td>35</td>
                    <td>Judah Manning</td>
                    <td>(332) 888-8768</td>
                    <td><a href="mailto:#">congue@Nuncut.com</a></td>
                    <td>07/16/1974</td>
                    <td>December 14, 2009</td>
                    <td>9</td>
                    <td>34%</td>
                    <td>$95,173</td>
                    <td>6.9</td>
                </tr>
                <tr>
                    <td>36</td>
                    <td>Yoshi Humphrey</td>
                    <td>(961) 215-0426</td>
                    <td><a href="mailto:#">pharetra@rutrumFusce.edu</a></td>
                    <td>01/13/1962</td>
                    <td>August 24, 1997</td>
                    <td>4</td>
                    <td>84%</td>
                    <td>$140,552</td>
                    <td>3.9</td>
                </tr>
                <tr>
                    <td>37</td>
                    <td>Idona Williams</td>
                    <td>(163) 580-2609</td>
                    <td><a href="mailto:#">Integer.aliquam@Sedetlibero.org</a></td>
                    <td>08/27/1967</td>
                    <td>February 22, 2000</td>
                    <td>-3</td>
                    <td>15%</td>
                    <td>$37,762</td>
                    <td>5.9</td>
                </tr>
                <tr>
                    <td>38</td>
                    <td>Petra Bennett</td>
                    <td>(976) 799-4111</td>
                    <td><a href="mailto:#">Proin@Donecelementum.org</a></td>
                    <td>01/02/1959</td>
                    <td>April 27, 1999</td>
                    <td>2</td>
                    <td>81%</td>
                    <td>$32,371</td>
                    <td>6.5</td>
                </tr>
                <tr>
                    <td>39</td>
                    <td>Phyllis Rogers</td>
                    <td>(798) 959-3321</td>
                    <td><a href="mailto:#">eget.dictum.placerat@idlibero.ca</a></td>
                    <td>11/27/1961</td>
                    <td>February 14, 2009</td>
                    <td>-10</td>
                    <td>42%</td>
                    <td>$77,847</td>
                    <td>4.9</td>
                </tr>
                <tr>
                    <td>40</td>
                    <td>Fritz Benton</td>
                    <td>(525) 353-2984</td>
                    <td><a href="mailto:#">a@diamnunc.com</a></td>
                    <td>10/02/1957</td>
                    <td>June 16, 2002</td>
                    <td>-5</td>
                    <td>2%</td>
                    <td>$75,654</td>
                    <td>8.9</td>
                </tr>
                <tr>
                    <td>41</td>
                    <td>Mannix Davidson</td>
                    <td>(106) 260-1651</td>
                    <td><a href="mailto:#">pulvinar@Duisvolutpat.org</a></td>
                    <td>08/24/1964</td>
                    <td>October 24, 2002</td>
                    <td>1</td>
                    <td>93%</td>
                    <td>$11,498</td>
                    <td>2.5</td>
                </tr>
                <tr>
                    <td>42</td>
                    <td>Grant Lawrence</td>
                    <td>(807) 487-5786</td>
                    <td><a href="mailto:#">a@interdumlibero.ca</a></td>
                    <td>10/10/1973</td>
                    <td>March 28, 2007</td>
                    <td>9</td>
                    <td>6%</td>
                    <td>$137,743</td>
                    <td></td>
                </tr>
                <tr>
                    <td>43</td>
                    <td>Laurel Ortiz</td>
                    <td>(945) 481-7808</td>
                    <td><a href="mailto:#">laoreet.posuere@vallis.com</a></td>
                    <td>08/19/1962</td>
                    <td>May 11, 2006</td>
                    <td>5</td>
                    <td>55%</td>
                    <td>$64,799</td>
                    <td></td>
                </tr>
                <tr>
                    <td>44</td>
                    <td>Lewis Frank</td>
                    <td>(844) 314-8683</td>
                    <td><a href="mailto:#">fames@gravida.edu</a></td>
                    <td>12/01/1966</td>
                    <td>January 25, 1998</td>
                    <td>5.5</td>
                    <td>30%</td>
                    <td>$99,927</td>
                    <td></td>
                </tr>
                <tr>
                    <td>45</td>
                    <td>Yasir Knox</td>
                    <td>(814) 509-0367</td>
                    <td><a href="mailto:#">Cras.vulputate.velit@acusUt.com</a></td>
                    <td>10/23/1981</td>
                    <td>August 20, 2007</td>
                    <td>-2</td>
                    <td>61%</td>
                    <td>$36,618</td>
                    <td></td>
                </tr>
                <tr>
                    <td>46</td>
                    <td>Palmer Newman</td>
                    <td>(955) 748-1014</td>
                    <td><a href="mailto:#">fringilla@id.edu</a></td>
                    <td>10/29/1980</td>
                    <td>December 28, 2007</td>
                    <td>-9</td>
                    <td>85%</td>
                    <td>$19,325</td>
                    <td></td>
                </tr>
			</tbody>
		</table>
		
 		</div>
 		<div class="notes">
 			<h4>Notes</h4>
	 		<ol>
	 			<li>The <a name="draws"><em>Draws</em></a> column includes both mutually agreed draws and 'Did Not Finish' games from the spreadsheet.</li>
	 			<li>Data was updated <fmt:formatDate value="${lastUpdate}" dateStyle="FULL" type="both" /></li>
	 		</ol>
 		</div>
		<jsp:directive.include file="foot-boilerplate.xml" />

		<script type="text/javascript" src="${app.jsPath}/tinytable.js"><!-- @#$! jsp compiler --></script>
		<script type="text/javascript"> 
			var sorter = new TINY.table.sorter('sorter','table',{
				headclass:'head',
				ascclass:'asc',
				descclass:'desc',
				evenclass:'evenrow',
				oddclass:'oddrow',
				evenselclass:'evenselected',
				oddselclass:'oddselected',
				paginate:false,
				size:10,
				colddid:'columns',
				currentid:'currentpage',
				totalid:'totalpages',
				startingrecid:'startrecord',
				endingrecid:'endrecord',
				totalrecid:'totalrecords',
				hoverid:'selectedrow',
				pageddid:'pagedropdown',
				navid:'tablenav',
				sortcolumn:1,
				sortdir:1,
				init:true
			});
		</script> 
  	</body>
</html>
</jsp:root>
