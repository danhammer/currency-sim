# Overview

The objective of this project is to simulate the hylo marketplace
under different parameterizations or market structures.  To do so, an
exchange economy will be built from scratch, knowing that some level
of top-down manipulation is desirable.

### Running simulations

If you have Git and lein already installed, you're all set:

```bash
./hylo-sim.sh help
```

Otherwise, to easily download everything and run simulations on your Mac, 
follow these steps:

1. Download and install [Git](https://central.github.com/mac/latest).
1. Download and save the [hylo-sim bootstrap.sh](https://github.com/Hylozoic/hylo-sim/raw/master/bootstrap.sh) script to your Desktop.
1. Open your Terminal app.

Your Terminal app gives you a command line interface. You're now ready to
bootstrap the hylo-sim software. In the Terminal window, type:

```bash
cd ~/Desktop
./bootstrap.sh
```

That will take a little while to download and install everything! When it
is done, type this in your Terminal window:

```bash
cd hylo-sim
./hylo-sim.sh help
```

That lists all the options for running a simulation. Here's an example
command line for running a simulation:

```bash
./hylo-sim.sh -h 100 -u 100 -i 20 -v 1 -d 1.5 -e 5 -w /tmp/hylo.png
```

When that finishes, you'll have a simulation graph in `/tmp/hylo.png`
to view.

# The Hylo Economy

## hylo exchange

The ultimate goal of the hylo exchange is to surface good project
ideas, based on crowd sourced opinion.  Funders show support for
viable ideas with hylos; more hylos imply more support, and thereby a
worthy idea.  As with any currency, though, the hylos are
characterized by two properties: (1) hylos are a unit of accounting;
and (2) they store value, if only the opportunity support a future
idea.  The central bank adjusts fiscal parameters to control the hylo
supply in active exchange.  Too few hylos or too many hylos in active
exchange will detract from the ultimate objective of identifying
worthy ideas -- and potentially identifying individuals who are
especially good at identifying worthy ideas.

## structure of the game

Users are both suppliers and consumers of ideas.  The structure of the
overall game should be determined by the underlying incentives of
users as both consumers and suppliers of ideas.  The market structure
will maintain user incentives at any level of hylo accummulation.  For
this, hylos must enter and exit the market, such that the flows
provide the _appropriate_ opportunities to invest time and effort in
identifying quality ideas.

On the **supply side**, the hylo exchange supports the notion that
ideas can be created, branched, merged, buried, or promoted to a
campaign.  The ability to transfer hylos from users to ideas or from
the central bank to ideas will guide effort toward this natural
engagement with ideas.  

On the **demand side**, under the dividend structure, users will
demand _quality_ ideas because they offer the opportunity to
accummulate hylos, which have inherent value.  The value is derived
from the ability to allocate hylos to maintain an idea _or_ to convert
the hylos into some metric of reputation or actual goods or services.
You have the opportunity in this economy to surface both good ideas
_and_ users that can identify good ideas.  A sort of second-life for
venture capitalists, where everyone starts at a level playing field.
The hylo exchange may actually disperse the search costs for venture
capital firms looking for new talent.  If VC firms derive actual,
real-life value from the hylo signal, then hylos will have non-zero
value in exchange -- and they can be treated as a scarce resource with
all the trappings of economic theory.

## users earning hylos

**1.** Leverage the crowd's ability to identify quality
  projects. Effort and interest are scarce resources, and earning
  hylos should incentivize expending effort.  Let the users parse
  signal from ambient noise to find decent implementations of good
  ideas.

**2.** Manipulating dividend rates offer a fiscal lever to manage hylo
  supply.

**3.** In response to Edward's comment: "So while an individual's
  Hylos may be 'scarce'-- their creation is not by Fiat bank debt
  money a-la the Fed, but they are created by the evidencing of the
  creation of shared value for the commons." Perhaps tag the dividend
  rates to some metric of popularity.  More popular ideas will receive
  higher rates, with diminishing returns.  Early actors will garner a
  higher rate of returns than those that jump on the bandwagon.

**4.** In response to Edward's comment: "the number of 'projects' that
  emerge referring to an influential idea, and the number of times an
  idea's 'children' do the same." Since an idea can be branched, the
  dividend structure is not totally straightforward; but the correct
  reward structure can be maintained with a sort of royalty scheme.
  While this may sound complicated, I don't think it will ultimately
  be that hard to implement and explain to users, given that it is a
  fairly common mechanism.

## users losing hylos

**1.** Disincentivize the haphazard creation of poor quality ideas.

## hylos enter exchange

## hylos exit exchange

**1.** Depreciating hylos to spur growth?  Maybe other means to
  indicate stagnating ideas without changing the relative valuation.

## constraints

**1.** Prevent superusers, convert hylo accummulation to reputation
  accummulation; potentially through a bond-type mechanism.

## valuation of external effects

Edward noted that the ideal reward structure for an idea will include
"a way to measure and award dividends that reflect the power of an
idea to affect the well-being of others, and the health of the
community and the commons."  In principle, I totally agree.  This is
_the central consideration_ in environmental economics, where the
valuation of external effects is of primary importance.  The problem
is who gets to determine the social value of the idea, when its
benefits (or costs) extend beyond the scope of the market exchange.  I
think that the hylo exchange should be unencumbered by seemingly
arbitrary valuations; and any valuation of external or community
benefits that occur outside the hylo exchange will ultimately detract
from the objective of surfacing quality ideas.  That said, notable
ideas can receive extra-market benefits like "featured status" or free
publicity, which has value but _does not affect the relative pricing
of quality ideas._

## limited scope

The type of projects that would be funded are limited.  Big,
game-changing ideas will not be prematurely released for crowd ideas.
And in fact, it's unclear whether the crowd would be able to identify
these anyway.  The projects that are decent candidates for hylo
support are those that are a novel approach to an existing idea with
an excellent implementation.  I may be wrong about this, but it's a
hunch.
