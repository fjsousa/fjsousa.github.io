Subtitle: Culture is a big part of the carbon based entities that do pretty much all the computer programming these days. This blog post is my first take on the other fluffy aspects of software development. I'll argue why being a language fundamentalist won't safeguard you from producing a bad system.
Title: Avoiding the pitfall of language fundamentalism. Why the obsessive pursuit of language mastery will not translate in results.
Tags: Code
Date: 2017 07 13
Skip-post-walk: true

# Avoiding the pitfall of language fundamentalism. Why the obsessive pursuit of language mastery will not translate in results.

I've been working with Clojure for the past year professionally and it has been an amazing experience. The language is built around sound computer science concepts and you learn so much that transfers to other languages, just by watching Rick Hicks' talks. I'd recommend [Hammock Driven Development](https://www.youtube.com/watch?v=f84n5oFoZBc), [The Value of Values](https://www.infoq.com/presentations/Value-Values) and the classic [Simple made easy](https://www.infoq.com/presentations/Simple-Made-Easy). Just the way it gets you thinking about concepts like immutability, values, state, why pure functions are the way to go, for me, it made me a better software developer. Clojure is feature rich and it aims at making your life easier when producing maintainable code. There's an extensive core library that will make you feel incredibly productive once you learn it, there are features for concurrent and parallel programming, a great macro system, an incredible Repl that speeds the iteration cycle, not to mention the java interop that lets you access the multitude of libraries in java land.

Clojurians pride themselves of having all these tools available and developer happiness runs high among them. However, given all these things to learn, I've found myself obsessed  with the language fundamentals and adopting a purist mindset. It's easy to agree that mastery of the tools equals better results, and although this pursuit is part of the learning process, I'll argue why this type of perfectionism is an unhelpful belief. Mastery of tools, although a necessary condition to do a decent work, will not safeguard you from delivering poor results.

> "Learning the fundamentals and building a system, are actually distinct pursuits."

Learning a language takes time and complex and rich languages take necessarily more time to learn than less complex ones. As professional software engineers, our job is to code in some language. Diligent software engineers will have the drive to invest extra time in learning the different aspects of the language. However, realize that learning the fundamentals of a language and using the language as a tool to do your job are actually two distinct pursuits. Over time, you can specialize and be productive in the two, but you need to prioritise one over the other at a given time.

> "... although it is certainly helpful for the author to be able to reason about linguistics at some level, knowing everything there is to know about it is not going to help him sell more books."

A best-selling author certainly has to have a high knowledge of the language he writes in, however he would get 10 nil from a linguist regarding its correct use. Although both of them work with language, it's clear that the two
of them are on a very different professional trajectory. And although it is certainly helpful for the author to be able to reason about linguistics at some level, knowing everything there is to know about it is not going to help him sell more books. By choosing to focus on the language fundamentals, the developer is making a decision to pursue one path rather than the other. And this has important implications. If knowing the language is not sufficient to get the job done right, what other factors are there?

The last three paragraphs can be written in a different way: knowing the language is not sufficient to produce a functional and efficient system. Putting a system together requires skills other than knowing the language. Among other things, you need to reason about priorities and the domain of the problem you're working with. The former has implication on what gets built and refactored while the latter, has implications on separation of concerns. Both have deep impact in the way the application is architected. A developer with a good fundamental grasp of the language is in a much better position to create a good system than one that doesn't have that knowledge, however, that knowledge alone doesn't safeguard her from falling very short from what could have been achieved.

> "Knowing the language is not sufficient to produce a functional and efficient system."


Do I mean with all this that you should disregard learning the tools and stack overflow your way through the requirements? No, it means that it's necessary to factor in other things when deciding how to reason about your work. The conclusion is that what's necessary is to switch the focus according to the problem at hand and your motivation. Is worrying about the structure and fundamentals of the language, related to the task at hand, or is the need to iterate and produce a functional, readable first pass of a system the current objective. Both are valid pursuits, but keep in mind that they do not intersect at all times.

(Edited 3/12/2020: originally published at datajournal.co.uk)
