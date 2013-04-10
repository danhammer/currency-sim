#!/bin/bash

# Get the hylo-sim code:
echo 'Getting the hylo-sim code...'
git clone https://github.com/Hylozoic/hylo-sim.git
cd hylo-sim
git checkout feature/cli

# Install lein:
echo 'Installing lein...'
curl -O https://raw.github.com/technomancy/leiningen/stable/bin/lein
mv lein lein1
curl -O  https://raw.github.com/technomancy/leiningen/preview/bin/lein
chmod u+x lein lein1
./lein

echo 'Done! Now type in:'
echo 'cd hylo-sim'
echo './hylo-sim.sh help'



 
