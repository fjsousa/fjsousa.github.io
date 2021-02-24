Title: Lasef Youtube channel - a series of turbulence visualizations
Date: 2021 02 22
Tags: Engineering,Videos
Subtitle: Around 2012 when I was a research assistant at Technical University of Lisbon, I put together a youtube channel for my fluid simulation lab. A fluid flow professor I had used to joke that CFD - computational fluid dynamics - should be called colourful fluid dynamics instead. The truth is, they make amazing youtube content, with some of the videos in the channel reaching past 8k views 9 years later.
Thumb: thumb.png
Thumb-alt: Visualization of DNS simulation (scalar)
Grid-media-item: <a target="_blank" href="https://www.youtube.com/watch?v=uI-KrtV0PJA" title="Visualization of DNS simulation"><img alt="Visualization of DNS simulation" src="/assets/img/lasef-turbulence-youtube/scalar_dns.png"></a>


# Lasef Youtube channel - a series of turbulence visualizations

Around 2012 when I was a research assistant at Technical University of Lisbon, I put together a [youtube channel](https://www.youtube.com/user/lasefist/featured) for my fluid simulation lab - [Lasef](http://www.lasef.ist.utl.pt/). A fluid flow professor I had used to joke that CFD - computational fluid dynamics - should be rather called colourful fluid dynamics instead. The truth is, they make amazing youtube content, with some of the videos in the channel reaching past 8k views 9 years later.

These are just some highlights of the channel. I didn't author any of the simulations, I only put together the channel and compiled the early works.

<a target="_blank" href="https://www.youtube.com/watch?v=uI-KrtV0PJA" title="Visualization of DNS simulation"><img alt="Visualization of DNS simulation" src="/assets/img/lasef-turbulence-youtube/scalar_dns.png"></a>

Richard Feynman said about [turbulence](https://en.wikipedia.org/wiki/Turbulence) that it "is the most important unsolved problem of classical physics." A big chunk of the research at Lasef was to carry out high resolution direct simulation of the Navier-Stokes equations. These would be carried out using high performance computing techniques, in a cluster spanning many nodes. The goal was to simulate small portions of fluid with a very high resolution, up to machine precision so that turbulence models could be created. These would later be included in less precise but bigger scale simulations, where the small scales of the fluid flow couldn't be resolved precisely, like weather models.

<a target="_blank" href="https://www.youtube.com/watch?v=biiNeX8VmoE" title="Insect flight - 2d flapping wings"><img alt="Insect flight - 2d flapping wings" src="/assets/img/lasef-turbulence-youtube/insect-flight.png"></a>

A 2d flexible membrane can be simulated to approximate the flapping of an insect wing. This is an example of a use case using a commercial software, Start-CCM in this case. In the video you can see the basic functioning of the wind: blue means low pressure, when the wing flaps down, the negative pressure on top pull the wing up. To put it another way, when the wing pushes the fluid down, the fluid pushes the wing up.

<a target="_blank" href="https://www.youtube.com/watch?v=bIWLsX979Ok" title="Supersonic 2d flow"><img alt="Supersonic 2d flow" src="/assets/img/lasef-turbulence-youtube/supersonic-flow.png"></a>

This is a super sonic flow over a cavity done with an open source CFD code called OpenFOAM. You can see the discontinuation (shock wave) showing up, rupturing through space-time.
