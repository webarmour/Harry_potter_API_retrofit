package com.example.harry_potter_and_retrofit.lessons.dagger

import javax.inject.Inject

class MagicWand @Inject constructor(
    coreOfWand: CoreOfWand,
    woodOfWand: WoodOfWand,
)
