# SNLP_MiniProject

## Team: Mavericks
* Rajat Khanna: 6826666
* Timur Musav:6810518
* Zain Ul Abidin: 6819846

## Configuration:
Running of this project requires three parameters to be passed through the main args. 
1. Name and Path of input file
2. Encoding type
3. Name and Path of output file (ttl format file)

Parameters can be set in the Run/Debug configuration's Program arguments.

E.g Programs Arugments: "C:\SNLP_Submit\SNLP_MiniProject\Data\test.tsv" "UTF-8" "C:\SNLP_Submit\SNLP_MiniProject\Data\result.ttl"

## How to build the Project?
Open the solution in the IntelliJ and run the solution. Make sure parameters are set in the Run/Debug configuration as mentioned
in the above section.

## Approach:
1. In the training and test data, we have predefined relations between the facts. Relations are subordinate,subsidiary,author,star,foundation,team,
subordinate,death place,birthplace,Award,spouse. In the data we had different variations of the relations like nascence place is a variation of birthplace.
We have mapped the variations of the relation to the common labels.

2. In the facts, we have different arrangements of the facts. In some facts, we have a relation like Person: deathPlace: Location and in certain facts
we have Location: deathPlace Person: In our implementation we have brought these variations into the uniform order. In order to bring these in the
uniform order, we have also normalized the facts by removing special characters.

3. Once the facts are arranged in the uniform format then based on the Subject which is in most of the Relations was Person except foundation place relation, then we have tried to construct the Wikipedia URL and then verified the Wikipedia URL, If the Url is verified then the content of the Wikipedia is
fetched. From the Wikipedia content, almost all the information pertaining to the subject was available in the infobox which appears on the top right corner of the page, and we are extracting that content.

4. Once the content is extracted then based on relation certain labels contain the relevant information about the relation. For example, if we take the example of role relation then Role's relevant words are Prime Minister, President, Chancellor or Opposition leader etc. For each relation, we have
defined the list of words which can be present inside the infobox content. If the relevant word is found in the infobox's content then we try to match value defined for the label. If the value is matched then we mark the fact as true:1 otherwise we mark it as false:0. For better understanding lets take
the following example: "Pakistan is Benazir Bhutto's role. 1.0"
First the fact "Pakistan is Benazir Bhutto's role" is converted into the Benazir Bhutto role Pakistan format. 
In our approach, relevant words for Role are "chancellor of", "prime minister of", "leader of opposition", "politician", 
If infobox contains any of the above words then we try to match the object value which is Pakistan in our case and assigned the fact as true 1.0. 

*If we are not able to locate valid URL of the fact we assign the fact-value as -1.0.
