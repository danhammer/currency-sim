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

![](http://i.imgur.com/tOBqCFq.png)
