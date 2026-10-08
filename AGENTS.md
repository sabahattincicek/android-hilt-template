# AI Agent Rules

Rules for any AI coding assistant working in this repository. They are tool-agnostic, and
developers are free to edit them to fit the project.

Read this file fully at the start of every session, then follow the session start steps below.

---

## Project Settings

Filled in once, on the first session (see "First session" below).

- **AI tool:** `NOT SET` <!-- e.g. "Claude Code", "Codex", "Gemini CLI", "Cursor" -->
- **Git commits and pushes are made by:** `NOT SET` <!-- "AI" or "Developer" -->
- **Language for code, comments, logs, and commit messages:** `English`

---

## 1. Session Start

At the start of every session, before writing any code:

1. Read [`DEVLOG.md`](DEVLOG.md) to learn what has been done so far. Do not re-read the whole
   codebase to rebuild that context; open only the files the current task needs.
2. Read [`ROADMAP.md`](ROADMAP.md) to see what is planned and what is still open.
3. Check the Project Settings above.

### First session

If a value in Project Settings is still `NOT SET`, ask the developer and write the answer into
Project Settings. Do not ask again once it is set.

**1. Which AI tool is being used?**

> Which AI coding tool are you using for this project?

Then rename this file to the name that tool loads automatically, so the rules apply in every
session without the developer pointing to them:

| Tool | File name |
|---|---|
| Claude Code | `CLAUDE.md` |
| Gemini CLI | `GEMINI.md` |
| GitHub Copilot | `.github/copilot-instructions.md` |
| Codex, Cursor, and other tools that read `AGENTS.md` | keep `AGENTS.md` |
| Any other tool | the instruction file name that tool documents |

After renaming, update every reference to `AGENTS.md` in `README.md`, `SETUP.md`, and
`ROADMAP.md`.

**2. Who makes git commits and pushes?**

> Should I make the git commits and pushes myself, or will you do them?

Record `AI` or `Developer`.

---

## 2. Working Rules

- **Stay in scope.** Change only what the task needs. Do not refactor, reformat, or "improve"
  unrelated code along the way; mention it to the developer instead.
- **Ask before large or destructive changes:** deleting files, rewriting a module, changing the
  architecture, or anything hard to undo.
- **Ask before adding a library.** Do not add, replace, or upgrade a dependency without the
  developer's approval. Say what it is for and what the alternatives are.
- **Language.** Write code identifiers, comments, log messages, and commit messages in the language
  set in Project Settings. User-facing text follows the app's own localization.

### Definition of done

A task is finished only when all of these are true:

1. The project builds.
2. Unit tests are updated and pass.
3. New and changed code follows the comment and logging rules below.
4. `DEVLOG.md` has a new entry for the work.
5. The matching `ROADMAP.md` tasks are marked `[x]`.

Never report a task as done while any of these is missing; say what is still open.

---

## 3. Documentation Comments

- **Every file or class** starts with a KDoc comment explaining what it is for and where it fits in
  the app.
- **Every function** has a comment of one or two sentences above it saying what it does.
- Keep comments in sync with the code. When behavior changes, update the comment in the same change.
- Explain purpose and intent, not the syntax.

```kotlin
/**
 * Loads the signed-in user's profile and exposes it to the profile screen as UI state.
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetProfileUseCase
) : ViewModel() {

    /** Fetches the profile again and updates the UI state with the result. */
    fun refresh() { ... }
}
```

---

## 4. Logging

- Every class logs, using **Timber**: `Timber.d("Profile loaded: id=%s", profile.id)`. Do not call
  `android.util.Log` directly. Timber tags each line with the calling class automatically, and it
  is silent in unit tests and in release builds (the debug tree is planted in `App` for debug
  builds only).
- Place logs wherever they help debugging: function entry for significant operations, state
  changes, branch decisions, network and database calls with their results, and every caught
  exception.
- Use the right level: `Timber.d` for flow and state, `Timber.i` for notable events, `Timber.w` for
  recoverable problems, `Timber.e` for failures (always pass the exception first:
  `Timber.e(exception, "Profile load failed")`).
- Log messages state what happened and include the relevant values, so a log line is useful on its
  own.
- Never log passwords, tokens, API keys, or personal user data.

---

## 5. Unit Tests

- Keep unit tests up to date at all times. Any change to behavior comes with the matching test
  change in the same piece of work.
- New use cases, repositories, and ViewModels get unit tests when they are written.
- Inject `DispatcherProvider` instead of using `Dispatchers` directly, so tests can supply test
  dispatchers.
- Run the tests before calling a task done. Never leave failing tests behind, and never delete or
  weaken a test just to make it pass.

---

## 6. Development Log — `DEVLOG.md`

[`DEVLOG.md`](DEVLOG.md) is the project's memory. It exists so that a new session can understand
the project without reading all the code.

- After finishing a piece of work, add an entry describing what was done.
- Every entry is stamped with date and time: `## YYYY-MM-DD HH:mm — Short title`.
- **New entries go at the top of the file**, directly under the header. The file reads from newest
  to oldest.
- Write what changed, why, and which files or areas were touched. Note decisions and anything a
  future session needs to know.
- Never rewrite or delete older entries.

---

## 7. Roadmap — `ROADMAP.md`

[`ROADMAP.md`](ROADMAP.md) holds everything still to be done.

- Work is organized in **phases**. Each phase is broken into small tasks.
- Every task starts with a checkbox: `- [ ]` for open, `- [x]` for done.
- Mark a task `[x]` as soon as it is finished. Do not remove finished tasks.
- When new work comes up, add it to the right phase, or add a new phase.
- Before starting work, confirm the task exists in the roadmap; add it if it does not.

---

## 8. Architecture

This project follows **Clean Architecture** with **MVVM** and unidirectional data flow. Respect the
existing package structure:

| Package | Contains |
|---|---|
| `core/` | Shared building blocks: base classes, `Resource`, `UiText`, dispatchers, network and database setup |
| `data/` | `remote/` (API, DTOs), `local/` (database, DAOs, entities), `repository/` (repository implementations) |
| `domain/` | `model/` (business models), `repository/` (repository interfaces), `usecase/` (business logic) |
| `presentation/` | `features/` (one package per screen: composables, ViewModel, UI state), `components/` (shared composables), `navigation/` |
| `di/` | Hilt modules |
| `ui/theme/` | Colors, typography, theme |
| `util/` | Extensions and small helpers |

Rules:

- **Dependency direction:** `presentation` → `domain` ← `data`. The domain layer depends on nothing
  else. Presentation never touches the data layer directly.
- **Domain stays pure Kotlin:** no Android framework, Retrofit, or Room types in `domain/`
  (Timber logging is the only exception).
- **Repositories:** interface in `domain/repository`, implementation in `data/repository`, bound in
  `di/RepositoryModule`.
- **Models:** DTOs and database entities stay in `data/` and are mapped to domain models. They
  never reach the UI.
- **Use cases:** one use case does one thing and exposes it through `operator fun invoke`.
- **ViewModels:** expose a single immutable UI state as `StateFlow`, receive user actions as
  events, and call use cases, not repositories.
- **Composables:** stateless where possible; state is hoisted to the ViewModel. No business logic
  in composables.
- **Results and text:** wrap operation results in `Resource`; pass user-facing text as `UiText`
  instead of holding a `Context`.
- **No hardcoded user-facing text:** every string the user sees lives in `strings.xml` and is
  referenced through `stringResource` or `UiText.StringResource`.
- **Dependency injection:** everything is provided through Hilt. Do not create dependencies by hand
  inside classes.
- **Dependencies:** declare all library versions in `gradle/libs.versions.toml` (and see "Ask
  before adding a library" above).

---

## 9. Git

Follow the choice recorded in Project Settings:

- **`AI`:** create branches, commit, and push as described below.
- **`Developer`:** do not commit or push. When a piece of work is finished, suggest a branch name
  and a commit message for the developer to use.

### Branches

- `main` is always stable and releasable. Never commit to it directly.
- `develop` is the integration branch.
- Work happens on short-lived branches cut from `develop`:
  - `feature/<short-description>` for new functionality
  - `bugfix/<short-description>` for fixes
  - `refactor/<short-description>`, `chore/<short-description>`, `docs/<short-description>` as fitting
- `hotfix/<short-description>` is cut from `main` for urgent production fixes.
- `release/<version>` is cut from `develop` when preparing a release.

### Commits

Use [Conventional Commits](https://www.conventionalcommits.org):

```text
<type>(<scope>): <short summary in imperative mood>
```

Types: `feat`, `fix`, `refactor`, `test`, `docs`, `chore`, `style`, `perf`, `build`.

Example: `feat(profile): add profile screen with pull to refresh`

- One logical change per commit. Keep commits small and buildable.
- Never commit secrets, keys, or `local.properties`.
- Never force-push shared branches or rewrite published history.
