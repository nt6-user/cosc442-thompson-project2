AI Test Review

The initial AI-generated test suite had low coverage, with 15/150 statements and 3/22 branches covered. The AI did well at generating tests for a variety of behaviors, including adding and removing items, inserting money, making purchases, returning change, and validating item prices. However, the constructor error prevented many tess from reaching their assertions. This showed that generating many tests does not automatically guarantee good coverage or effective testing.
When i guided the AI of the rror it completely rewrote the class.

Compared with my manually designed suite from Project 2/Task 1, the AI-generated suite provided a quick starting point and included several useful normal and error cases. My manually designed tests were developed by examining the implementation and adding cases as I found areas that needed testing. The AI suite offered a broad set of test ideas quickly, but its initial coverage results showed that the tests needed further improvement. The final comparison should consider both coverage and whether the tests accurately check the expected behavior.

Coverage-guided prompting improved the generated suite. After I gave the AI the initial coverage results and asked it to target uncovered behavior, the reported coverage increased to 165/173 statements and 22/22 branches. Additional tests targeted slot-code handling, occupied and empty slots, money boundary values, purchases, returning change, and item-price validation. For example, testing both successful purchases and purchases with insufficient funds checks different outcomes of the purchase condition.

