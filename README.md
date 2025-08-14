# **P.A.P.A. — Unified UI (Android Monorepo)**

> **P.A.P.A.** (Personal-Aware Private Architecture) is the unified UI layer of the V.O.D.O.U. ecosystem. It delivers a consistent, device‑agnostic feel across Android surfaces while keeping data **local and permissioned**. No cloud. No broadcasts. **SurrealLabs cannot access your data** by design. *V.O.D.O.U. Certified* means it also works **independently of SurrealLabs services**.

## **Core Philosophy**

P.A.P.A. reflects the SurrealLabs mission: open, privacy-first, intuitive technology that makes interaction feel natural and immersive — without corporate overreach or invasive data collection.

**Core Principles:**

* **Local-first**: Data remains with the user — no hidden cloud dependencies.
* **Opt-in community**: Sharing is always a choice, never a requirement.
* **Hardware/Software synergy**: Built to integrate seamlessly with all V.O.D.O.U.-enabled devices.
* **No ads. No bloat. No nonsense.**
* **Accessible power**: Advanced capabilities, made usable by anyone.
* **Transparent binding**: In the V.O.D.O.U. ecosystem, “binding” is a consensual process. Both devices — and their users — agree to clear, transparent, and openly documented rules before interaction. There is no hidden control, no forced participation, and every binding can be ended at any time by the user.

Binding is not a generic “pairing.” It’s a purposeful, thematic handshake that grants mutual trust between devices. This process is inspired by the ceremonial feel of Vodou, but reimagined in a purely technical, user-first way — blending ritual-like clarity with uncompromising privacy.

---

## **Intended features & goals** *(living document — will evolve)*

* **Local-first, private by design:** No cloud, no broadcasts; data stays on-device.
* **Ritual binding:** Offline bind-only exchange (BLE primary, NFC optional, QR fallback) with LED/haptic signals (**13** on success), mirrored on-screen for accessibility.
* **Equilibrium (feel calibration):** Set `cm/360`, `px/cm`, `deg/s` so feel is consistent across devices/apps.
* **Verify suite:** Spin & Cursor tests with live metrics and pass/fail gates → “Equilibrium achieved.”
* **Familiar local (presence gate):** Once bound, entering a recognized locale auto-enables P.A.P.A. interactions (privacy-preserving local fingerprint).
* **Adapters:** Desktop / Main Engine / Editor so third‑party apps can apply Equilibrium without SurrealLabs API.
* **Certification copy:** Explicit API independence + privacy pledge in-app.
* **Accessibility:** Gesture/LED/haptic patterns always mirrored on-screen; works without sensors.
* **Multi-surface parity:** Phone, Wear OS, TV, Automotive OS; Android Auto (projection) optional.

**Non‑goals (here):** Cloud services, analytics/telemetry, remote config. P.A.P.A. is strictly **local‑first**. If you choose to add your own cloud services, we provide a guide for setting up a secure VPN using OpenVPN. This is entirely optional, and you are responsible for configuring it securely. In the future, we may offer a one‑click VPN setup through **L.E.G.B.A.** integration — but that will require a SurrealLabs network rack or another capable hardware platform that can reliably run OpenVPN.

---

## **Why P.A.P.A. exists**

P.A.P.A. delivers a **single, consistent interaction feel** across platforms. After binding a Certified device, a user can calibrate feel parameters, apply them to any P.A.P.A.-aware app, verify with Spin/Cursor tests, and receive clear feedback — all **without network dependency** and **without data ever leaving the device**.

It is built to embody the **people-first, open-source, privacy-respecting ethos** of SurrealLabs and the V.O.D.O.U. ecosystem, ensuring user empowerment and trust in every interaction.


This repository implements **Papa (the UI)** for:

* **Phone/Tablet** (Jetpack Compose)
* **Wear OS** (Compose for Wear OS)
* **Android TV** (Compose for TV / 10‑ft UI)
* **Android Automotive OS** (in‑car head units)

> Android Auto (phone projection) can be added later via the Car App Library (template UIs).

---

## What’s in Papa (scope of this repo)

Papa is the UI shell, patterns, and shared logic. It includes and orchestrates several features:

* **Equilibrium** — a feature of Papa

  * Calibrate targets: `cm/360`, `px/cm`, `deg/s`
  * Previews/tests so numbers *feel* identical across apps/devices
  * Local storage only (DataStore)
* **Bind‑only exchange**

  * Local pairing over BLE (primary), NFC tap (optional), QR fallback
  * No internet; short proof/attest locally, then store a **Bound** record
* **Verify suite**

  * Spin & Cursor tests with pass/fail gates → “**Equilibrium achieved.**”
* **Adapters (planned)**

  * Desktop / Main Engine / Editor adapters so third‑party apps can **apply Equilibrium settings** without any SurrealLabs API
* **Certification copy**

  * In‑app copy that states **API independence** and the **privacy pledge**

**Non‑goals (here):** cloud services, analytics/telemetry, remote config. Papa is strictly **local‑first**.

---

## Binding ritual (UX spec)

**Intent:** binding is a small ceremony, not a generic “Bluetooth pairing.” Any Papa‑aware device can bind to a Vodou‑enabled device using an offline, local exchange. No cloud.

### Ritual narrative

1. **Hold out your hand** to invite binding. The initiating device enters *Ritual* mode (discoverable, no internet).
2. Nearby devices that are also in *Ritual* mode approach; LEDs glow to indicate proximity.
3. On successful local attest, **both devices signal**: **one slow flash** then **three fast flashes** *(13 — Vodou theme)* and a matching haptic pattern (long, short×3).
4. Status changes to **Bound**. Settings and attest are stored locally.
5. **Unbind** by a mirrored gesture: **close your hand** and **shake for \~3s**; devices acknowledge with **three fast flashes then one slow**; record is cleared locally.

> All signaling is mirrored on‑screen (accessibility) and can be performed without physical gestures where sensors/LEDs aren’t available.

### Signal lexicon (durations)

* **Slow flash / long haptic:** 600–800 ms on, 400 ms off
* **Fast flash / short haptic:** 120–160 ms on, 120–160 ms off (repeat ×3)
* **Success:** 1× slow → 3× fast (13)
* **Unbind confirm:** 3× fast → 1× slow
* **Error/timeout:** 2× slow

### States & transitions

```
Idle → RitualInvite → Discoverable → CandidateDetected → LocalAttest →
  ↳ Success → Bound
  ↳ Fail/Timeout → Idle
Bound → UnbindInvite → UnbindConfirm → Idle
```

### Transports (all local‑only)

* **BLE (primary):** advertise short ephemeral ID; scan window ≤ 20s
* **NFC (optional):** tap to jump straight to attest
* **QR (fallback):** camera scans ephemeral code; show on either device

### Privacy guarantees

* No `INTERNET` permission; all secrets stay on‑device
* Ephemeral IDs, rotating nonces during `LocalAttest`
* Store only **Bound** record + device nickname locally (DataStore)

### Failure & safety

* Timeout (20s), collision (choose one candidate), stale QR, repeated attempts back‑off
* Full on‑screen controls for users without gesture/LED/haptics

### Developer hooks (interfaces)

* `RitualController` — drives state machine
* `RitualSignaler` — LEDs/haptics/sounds/screen glyphs
* `RitualTransport` — `ble` | `nfc` | `qr`
* `RitualStore` — persist/clear Bound record

### Acceptance (MVP)

* Enter Ritual on both devices → complete local attest → see **13** signal on both → **Bound** stored locally
* Unbind gesture (or on‑screen control) removes record and shows reverse **31** signal
* Works in airplane mode; no network activity

---

## Familiar local (presence gate)

**Intent:** once devices are **Bound**, you should only need to **enter a familiar local** to use them. No extra taps or cloud checks — presence in a known local unlocks Papa interactions.

### What is a "familiar local"?

A privacy‑preserving **local fingerprint** of a place you trust (e.g., your desk, studio, car cabin). It’s computed **on‑device** from nearby signals and never leaves the device.

**Anchors (examples; all optional):**

* Nearby **Wi‑Fi BSSID set** (hashed, no passwords) and strength bands
* Ambient **Bluetooth beacons** (MAC salted & hashed) / RSSI bands
* **Inertial/magnetic** micro‑signature of the space (short window FFT/hist)
* **Room acoustics** (low‑res spectral hash), ambient light range
* Coarse **GNSS/geofence** if available (rounded grid)

### Behavior

* **Auto‑enable:** If the current fingerprint matches any stored familiar local above a threshold, Papa surfaces Bound device controls automatically.
* **Away mode:** If no match, Papa stays locked to basic UI; you can trigger a **Quick Ritual** or use an **offline PIN** (optional) to proceed.
* **Multiple locals:** Add as many as you like (e.g., home desk, studio rig, car).

### Setup flow

1. From a Bound state, tap **“Mark this place as familiar”**.
2. Papa samples anchors for \~5–10 seconds and stores a compact, **salted hash** profile.
3. Give it a nickname ("Home desk", "Studio A", "Cabin").

### Matching

* Score = weighted sum across available anchors; hysteresis avoids flapping.
* Thresholds tuned to avoid accidental unlocks (target FAR < 1e‑4).
* All raw readings are discarded; only reduced/hashed features persist.

### Privacy & safety

* No `INTERNET` permission; profiles are local, encrypted at rest.
* You can **pause presence** (privacy mode) or **delete** any familiar local.
* Revoking a **Bind** also offers to delete its associated locals.

### Developer hooks

* `LocalSense` — collects ambient anchors (pluggable sources)
* `LocalMatch` — computes match score & hysteresis
* `FamiliarLocalStore` — CRUD for local profiles (DataStore/Encrypted)

### Acceptance (MVP)

* From Bound, create a familiar local; moving into/out of it toggles access.
* Works offline; no cloud calls. Full on‑screen alternative for users without sensors.

---

## Architecture

```
apps/
  app-phone     # Phone/Tablet shell (Compose + Nav)
  app-wear      # Wear OS shell
  app-tv        # Android TV shell (10‑ft)
  app-car       # Android Automotive OS shell
core/
  design        # Theme + tokens (Papa dark + purple)
  ui            # Reusable composables (headers, buttons, screens)
  equilibrium   # Equilibrium data & math (Equilibrium is a feature of Papa)
# planned
feature/
  bind          # Local bind-only exchange (BLE/NFC/QR)
  verify        # Spin/Cursor verification suite
adapters/
  desktop/ engine/ editor  # Host integrations (no SurrealLabs service required)
```

### Core modules (current)

* **core/design** — Material 3 theme in Papa palette (dark by default)
* **core/ui** — Composables for Home/Bind/Calibrate/Verify stubs
* **core/equilibrium** —

  * `EquilibriumSettings`: `cmPer360`, `pxPerCm`, `degPerSec`
  * `EquilibriumStore`: persistence via DataStore (Preferences)
  * `VerifyMath.expectedPxPerSecond(deg/s, px/cm, cm/360)`

### App shells

Thin shells per surface that load the shared UI/logic from `core`.

---

## Why Papa (Unified UI) exists

Deliver a **single, consistent interaction feel** everywhere Papa runs. After binding a Certified device, a user can set feel parameters, apply them to any Papa‑aware app, verify with Spin/Cursor tests, and receive clear feedback — all **without network dependency** and **without data ever leaving the device**.

**Principles**

* **Local‑first**: avoid `INTERNET` permission; use local transports for bind/verify
* **Private by design**: no analytics, no telemetry, no third‑party trackers
* **Consistent feel**: shared math/UI guarantees the same response curve
* **Modular**: shared core + feature modules + adapters; thin surface shells

---

## Getting started

### Requirements

* Android Studio (latest stable) with **JDK 17**
* Kotlin **2.0** with **Compose Compiler Gradle plugin** (applied via version catalog)

### Clone & open

```bash
git clone https://github.com/<you>/papa.git
cd papa
```

Open in Android Studio and let Gradle sync.

### AndroidX / Compose

* `gradle.properties` includes `android.useAndroidX=true`
* Compose versions are managed via the version catalog; TV Compose uses published alpha versions

### Run a target

* `app-phone` (phone/tablet)
* `app-wear` (Wear OS)
* `app-tv` (Android TV)
* `app-car` (Automotive OS)

You’ll see the Papa shells with **Bind → Calibrate (Equilibrium) → Verify** stubs.

> Permissions: none by default. When wiring Bind for real, expect **BLUETOOTH** and optional **NFC**.

---

## Configuration & data

* **Storage**: DataStore (Preferences), namespace `equilibrium`
* **Fields**: `cm/360°`, `px/cm`, `deg/s`
* **Verify math**: `expectedPxPerSecond = (deg/s ÷ 360) × (cmPerTurn) × (pxPerCm)`

---

## Roadmap

* **Bind‑only exchange** (Phone first): BLE + QR + optional NFC; local attest; store Bound record
* **Verify suite**: live metrics, pass/fail, stored outcomes; “Equilibrium achieved” feedback
* **Adapters**: desktop / engine / editor bridges
* **Certification**: in‑app copy and UI badge that state API independence & privacy pledge
* **Android Auto (projection)**: optional module using Car App Library templates

---

## Development

* Keep shared logic/UI in `core`; keep app shells thin
* Compose + Material 3 everywhere
* **No analytics/telemetry**; no cloud dependencies

### CLI build

```bash
./gradlew :app-phone:assembleDebug :app-wear:assembleDebug :app-tv:assembleDebug :app-car:assembleDebug
```

### Testing

Start with unit tests for equilibrium math & persistence; add screenshot tests for key composables.

---

## Contributing

* Branch names: `feat/...`, `fix/...`, `chore/...`
* Commit style: `type(scope): summary` (e.g., `feat(bind): BLE pairing UI`)
* Include screenshots/GIFs for UI changes
* CI must be green

---

## Security & privacy

* No analytics/telemetry
* No network dependency by default
* Any permission/dependency with privacy impact must be justified in PR description and reflected here

---

## License

Apache License 2.0 — see `LICENSE`. Attribution via `NOTICE`.
