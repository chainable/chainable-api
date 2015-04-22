# chainable
[![Build Status](https://travis-ci.org/bemobi/chainable.svg?branch=master)](https://travis-ci.org/bemobi/chainable)

A commons-chains clone with our flavor of modifications 

## Overview
![ArchitecturalOverview](http://i.imgur.com/mWnohTV.png)
We use [_apache commons-chains_](http://commons.apache.org/proper/commons-chain/) project in a platform at [Bemobi](http://code.bemobi.com.br/blog/) and at some point it become a pain to maintain a catalog with hundreds of lines. Here comes main idea behind the chained project, to improve configurability, maintenability, to be less verbose, to ease the integration between our chains codebase with Spring and give a few tweaks.

Except from [_apache commons-chains_](http://commons.apache.org/proper/commons-chain/) project page:
>A popular technique for organizing the execution of complex processing flows is the ["Chain of Responsibility"](http://en.wikipedia.org/wiki/Chain_of_responsibility) pattern, as described (among many 
>other places) in the classic "Gang of Four" design patterns book. Although the fundamental API contracts
>required to implement this design patten are extremely simple, it is useful to have a base API that >facilitates using the pattern, and (more importantly) encouraging composition of command implementations >from multiple diverse sources.

In this scenario we have stateless commands executing logic and modifying the state of a context which will be shared along the chain. 

A common pattern is to have warm-up and clean up code to be executed before and afterwards the execution of a chain, so we have a special type of command called Filter which implements the [Pipelines and filters](http://webcem01.cem.itesm.mx:8005/apps/s200911/tc3003/notes_pipes_and_filters/) where at the beginning and end of any chain we are given the chance to execute some computation needed.

An in memory store is bundled - with a size of (Commands * k) where k is the revisions to be kept in memory  - as optional. The _RevisionGenerator_ is responsible to maintain _snapshots_ -or _diffs_- of context changes. It eases the time to undo some command executed earlier on using the [Memento Pattern](http://en.wikipedia.org/wiki/Memento_pattern).

## The almight Command
![Command](http://i.imgur.com/jfB7SGZ.png)

The Command is the main actor at the stage. It represents a unit of work. A Command has only one method: ```execute(ExecutionContext ctx)```. The command should retain no state, an act only upon the Execution Context that it receives as an argument A Command acts upon the state passed to it through a context object, but retains no state of its own. Commands together forms a Chain, so that a complex transaction can be created from logical executions of Commands. 

The possible returns of a Command are: 
* **CONTINUE:** the execution of the Chain will continue until it's finished. 
* **FINISHED:** ends the execution of the Current Chain (yes, they can be nested as we will see in a moment). Once a Chain if finished all the ```filter.postHandle(ExecutionContext ctx)``` are executed in the reversed order in which they are registered.
* A third scenario is when a **ERRORs** occur, the default behavior is to stop the chains and execute the Filters (again, in the reversed order in which they are registered), but you can choose to override or provide a custom ```SkipPolicy(Exception exceptions...)``` to continue the execution of the other commands/chains.


## Do you | and _Filter_?
![Filters](http://i.imgur.com/2Otvfng.png)

## It's all about _Context_
![Context](http://i.imgur.com/AQ5jZ2w.png)

## Chains 101
![Chain](http://i.imgur.com/M96Dq9M.png)
